/*
 * Copyright (c) 2018 Abex
 * Copyright (c) 2018, Daniel Teo <https://github.com/takuyakanbr>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *     list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copyright notice,
 *     this list of conditions and the following disclaimer in the documentation
 *     and/or other materials provided with the distribution.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 *  ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 *  WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 *  ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 *  (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 *  LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 *  ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 *  SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.runelite.client.plugins.timetracking;

import com.google.inject.Provides;
import java.awt.image.BufferedImage;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.coords.WorldPoint;
import net.runelite.client.events.ConfigChanged;
import net.runelite.api.events.ChatMessage;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.UsernameChanged;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import static net.runelite.client.plugins.timetracking.TimeTrackingConfig.CONFIG_GROUP;
import static net.runelite.client.plugins.timetracking.TimeTrackingConfig.STOPWATCHES;
import static net.runelite.client.plugins.timetracking.TimeTrackingConfig.TIMERS;
import net.runelite.client.plugins.timetracking.clocks.ClockManager;
import net.runelite.client.plugins.timetracking.farming.FarmingContractManager;
import net.runelite.client.plugins.timetracking.farming.FarmingTracker;
import net.runelite.client.plugins.timetracking.hunter.BirdHouseTracker;
import net.runelite.client.task.Schedule;
import net.runelite.client.ui.ClientToolbar;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.ui.NavigationButton;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;
import net.runelite.client.util.ImageUtil;

@PluginDescriptor(
	name = "Time Tracking",
	description = "Enable the Time Tracking panel, which contains timers, stopwatches, and farming and bird house trackers",
	tags = {"birdhouse", "farming", "hunter", "notifications", "skilling", "stopwatches", "timers", "panel"}
)
public class TimeTrackingPlugin extends Plugin
{
	private static final String CONTRACT_COMPLETED = "You've completed a Farming Guild Contract. You should return to Guildmaster Jane.";

	@Inject
	private ClientToolbar clientToolbar;

	@Inject
	private Client client;

	@Inject
	private FarmingTracker farmingTracker;

	@Inject
	private BirdHouseTracker birdHouseTracker;

	@Inject
	private FarmingContractManager farmingContractManager;

	@Inject
	private ClockManager clockManager;

	@Inject
	private ItemManager itemManager;

	@Inject
	private TimeTrackingConfig config;

	@Inject
	private InfoBoxManager infoBoxManager;

	@Inject
	private ScheduledExecutorService executorService;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private TimeTrackingOverlay overlay;

	private ScheduledFuture panelUpdateFuture;

	private TimeTrackingPanel panel;

	private NavigationButton navButton;

	private WorldPoint lastTickLocation;
	private boolean lastTickPostLogin;

	@Provides
	TimeTrackingConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(TimeTrackingConfig.class);
	}

	@Override
	protected void startUp() throws Exception
	{
		clockManager.loadTimers();
		clockManager.loadStopwatches();
		birdHouseTracker.loadFromConfig();
		farmingTracker.loadCompletionTimes();

		final BufferedImage icon = ImageUtil.getResourceStreamFromClass(getClass(), "watch.png");

		panel = new TimeTrackingPanel(itemManager, config, farmingTracker, birdHouseTracker, clockManager, farmingContractManager);

		navButton = NavigationButton.builder()
			.tooltip("Time Tracking")
			.icon(icon)
			.panel(panel)
			.priority(4)
			.build();

		clientToolbar.addNavigation(navButton);

		panelUpdateFuture = executorService.scheduleAtFixedRate(this::updatePanel, 200, 200, TimeUnit.MILLISECONDS);

		overlayManager.add(overlay);
	}

	@Override
	protected void shutDown() throws Exception
	{
		lastTickLocation = null;
		lastTickPostLogin = false;

		if (panelUpdateFuture != null)
		{
			panelUpdateFuture.cancel(true);
			panelUpdateFuture = null;
		}

		clientToolbar.removeNavigation(navButton);
		infoBoxManager.removeInfoBox(farmingContractManager.getInfoBox());
		farmingContractManager.setInfoBox(null);

		overlayManager.remove(overlay);
	}

	@Subscribe
	public void onConfigChanged(ConfigChanged e)
	{
		if (!e.getGroup().equals(CONFIG_GROUP))
		{
			return;
		}

		if (clockManager.getTimers().isEmpty() && e.getKey().equals(TIMERS))
		{
			clockManager.loadTimers();
		}
		else if (clockManager.getStopwatches().isEmpty() && e.getKey().equals(STOPWATCHES))
		{
			clockManager.loadStopwatches();
		}
	}

	@Subscribe
	public void onGameTick(GameTick t)
	{
		if (client.getGameState() != GameState.LOGGED_IN)
		{
			lastTickLocation = null;
			return;
		}

		// bird house data is only sent after exiting the post-login screen
		Widget motd = client.getWidget(WidgetInfo.LOGIN_CLICK_TO_PLAY_SCREEN_MESSAGE_OF_THE_DAY);
		if (motd != null && !motd.isHidden())
		{
			lastTickPostLogin = true;
			return;
		}

		if (lastTickPostLogin)
		{
			lastTickPostLogin = false;
			return;
		}

		WorldPoint loc = lastTickLocation;
		lastTickLocation = client.getLocalPlayer().getWorldLocation();

		if (loc == null || loc.getRegionID() != lastTickLocation.getRegionID())
		{
			return;
		}

		boolean birdHouseDataChanged = birdHouseTracker.updateData(loc);
		boolean farmingDataChanged = farmingTracker.updateData(loc);
		boolean farmingContractDataChanged = farmingContractManager.updateData(loc);

		if (birdHouseDataChanged || farmingDataChanged || farmingContractDataChanged)
		{
			panel.update();
		}
	}

	@Subscribe
	public void onUsernameChanged(UsernameChanged e)
	{
		farmingTracker.loadCompletionTimes();
		birdHouseTracker.loadFromConfig();
		farmingContractManager.loadContractFromConfig();
		panel.update();
	}

	@Subscribe
	public void onChatMessage(ChatMessage event)
	{
		if (event.getType() != ChatMessageType.GAMEMESSAGE || !event.getMessage().equals(CONTRACT_COMPLETED))
		{
			return;
		}

		farmingContractManager.setContract(null);
	}

	@Schedule(period = 10, unit = ChronoUnit.SECONDS)
	public void checkCompletion()
	{
		boolean birdHouseDataChanged = birdHouseTracker.checkCompletion();

		if (birdHouseDataChanged)
		{
			panel.update();
		}
	}

	private void updatePanel()
	{
		long unitTime = Instant.now().toEpochMilli() / 200;

		boolean clockDataChanged = false;
		boolean timerOrderChanged = false;

		if (unitTime % 5 == 0)
		{
			clockDataChanged = clockManager.checkCompletion();
			timerOrderChanged = clockManager.checkTimerOrder();
			clockManager.checkForWarnings();
		}

		if (unitTime % panel.getUpdateInterval() == 0 || clockDataChanged || timerOrderChanged)
		{
			panel.update();
		}
	}
}
