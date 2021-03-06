/*
 * Copyright (c) 2019, Jacky <liangj97@gmail.com>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.runelite.client.plugins.molchpearls;

import com.google.inject.Inject;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import net.runelite.api.Client;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.LineComponent;
import net.runelite.client.ui.overlay.components.PanelComponent;
import net.runelite.client.ui.overlay.components.TitleComponent;

public class MolchPearlOverlay extends Overlay
{
	private final Client client;
	private final MolchPearlPlugin plugin;
	private final MolchPearlConfig config;

	private final PanelComponent panelComponent = new PanelComponent();

	@Inject
	public MolchPearlOverlay(Client client, MolchPearlPlugin plugin, MolchPearlConfig config)
	{
		super(plugin);
		setPosition(OverlayPosition.TOP_LEFT);
		this.client = client;
		this.plugin = plugin;
		this.config = config;
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		if (!plugin.isInMolchIsland())
		{
			return null;
		}

		panelComponent.getChildren().clear();

		panelComponent.getChildren().add(TitleComponent.builder()
			.text("Molch Pearl Tracker")
			.color(Color.GREEN)
			.build());

		panelComponent.getChildren().add(LineComponent.builder()
			.left("Pearls Caught:")
			.right(Integer.toString(plugin.getTotalPearlsFound()))
			.build());

		if (config.showCurrent())
		{
			panelComponent.getChildren().add(LineComponent.builder()
				.left("Current Streak:")
				.right(Integer.toString(plugin.getCurrentFish()))
				.build());
		}

		if (config.showMin())
		{
			panelComponent.getChildren().add(LineComponent.builder()
				.left("Min Streak:")
				.right(Integer.toString(plugin.getMinFish()))
				.build());
		}

		if (config.showMax())
		{
			panelComponent.getChildren().add(LineComponent.builder()
				.left("Max Streak:")
				.right(Integer.toString(plugin.getMaxFish()))
				.build());
		}

		if (plugin.getTotalPearlsFound() > 1 && config.showAvg())
		{
			panelComponent.getChildren().add(LineComponent.builder()
				.left("Avg Streak:")
				.right(Integer.toString(plugin.getAvgFish()))
				.build());
		}

		if (config.showTotal())
		{
			panelComponent.getChildren().add(LineComponent.builder()
				.left("Caught Fish:")
				.right(Integer.toString(plugin.getTotalFish()))
				.build());
		}

		return panelComponent.render(graphics);
	}
}
