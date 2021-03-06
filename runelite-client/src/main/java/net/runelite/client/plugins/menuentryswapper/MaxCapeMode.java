package net.runelite.client.plugins.menuentryswapper;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MaxCapeMode
{
	CRAFTING("Crafting Guild"),
	TELE_HOUSE("Tele to POH"),
	OFF("Off");

	private final String name;

	@Override
	public String toString()
	{
		return name;
	}
}
