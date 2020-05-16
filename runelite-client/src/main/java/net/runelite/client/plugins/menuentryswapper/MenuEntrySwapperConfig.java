/*
 * Copyright (c) 2018, Adam <Adam@sigterm.info>
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
package net.runelite.client.plugins.menuentryswapper;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("menuentryswapper")
public interface MenuEntrySwapperConfig extends Config
{
	@ConfigItem(
		position = -2,
		keyName = "shiftClickCustomization",
		name = "Customizable shift-click",
		description = "Allows customization of shift-clicks on items"
	)
	default boolean shiftClickCustomization()
	{
		return true;
	}

	@ConfigItem(
		keyName = "swapAdmire",
		name = "Admire",
		description = "Swap Admire with Teleport, Spellbook and Perks (max cape) for mounted skill capes."
	)
	default boolean swapAdmire()
	{
		return true;
	}

	@ConfigItem(
		keyName = "swapAssignment",
		name = "Assignment",
		description = "Swap Talk-to with Assignment for Slayer Masters. This will take priority over swapping Trade."
	)
	default boolean swapAssignment()
	{
		return true;
	}

	@ConfigItem(
		keyName = "swapBanker",
		name = "Bank",
		description = "Swap Talk-to with Bank on Bank NPC<br>Example: Banker"
	)
	default boolean swapBank()
	{
		return true;
	}

	@ConfigItem(
		keyName = "swapBirdhouseEmpty",
		name = "Birdhouse",
		description = "Swap Interact with Empty for birdhouses on Fossil Island"
	)
	default boolean swapBirdhouseEmpty()
	{
		return true;
	}

	@ConfigItem(
		keyName = "swapBones",
		name = "Bury",
		description = "Swap Bury with Use on Bones"
	)
	default boolean swapBones()
	{
		return false;
	}

	@ConfigItem(
		keyName = "swapContract",
		name = "Contract",
		description = "Swap Talk-to with Contract on Guildmaster Jane"
	)
	default boolean swapContract()
	{
		return true;
	}

	@ConfigItem(
		keyName = "swapChase",
		name = "Chase",
		description = "Allows to left click your cat to chase"
	)
	default boolean swapChase()
	{
		return true;
	}

	@ConfigItem(
		keyName = "claimSlime",
		name = "Claim Slime",
		description = "Swap Talk-to with Claim Slime from Morytania diaries"
	)
	default boolean claimSlime()
	{
		return true;
	}

	@ConfigItem(
		keyName = "swapDarkMage",
		name = "Repairs",
		description = "Swap Talk-to with Repairs for Dark Mage"
	)
	default boolean swapDarkMage()
	{
		return true;
	}

	@ConfigItem(
		keyName = "swapCaptainKhaled",
		name = "Task",
		description = "Swap Talk-to with Task for Captain Khaled in Port Piscarilius"
	)
	default boolean swapCaptainKhaled()
	{
		return false;
	}

	@ConfigItem(
		keyName = "swapDecant",
		name = "Decant",
		description = "Swap Talk-to with Decant for Bob Barter and Murky Matt at the Grand Exchange."
	)
	default boolean swapDecant()
	{
		return false;
	}

	@ConfigItem(
		keyName = "swapExchange",
		name = "Exchange",
		description = "Swap Talk-to with Exchange on NPC<br>Example: Grand Exchange Clerk, Tool Leprechaun, Void Knight"
	)
	default boolean swapExchange()
	{
		return true;
	}

	@ConfigItem(
		keyName = "swapFairyRing",
		name = "Fairy ring",
		description = "Swap Zanaris with Last-destination or Configure on Fairy rings"
	)
	default FairyRingMode swapFairyRing()
	{
		return FairyRingMode.LAST_DESTINATION;
	}

	@ConfigItem(
		keyName = "swapHardWoodGrove",
		name = "Hardwood Grove",
		description = "Swap Quick-Pay(100) and Send-Parcel at Hardwood Grove"
	)
	default boolean swapHardWoodGrove()
	{
		return true;
	}

	@ConfigItem(
		keyName = "swapHarpoon",
		name = "Harpoon",
		description = "Swap Cage, Big Net with Harpoon on Fishing spot"
	)
	default boolean swapHarpoon()
	{
		return false;
	}

	@ConfigItem(
		keyName = "swapHelp",
		name = "Help",
		description = "Swap Talk-to with Help on Arceuus library customers"
	)
	default boolean swapHelp()
	{
		return true;
	}

	@ConfigItem(
		keyName = "swapHomePortal",
		name = "Home",
		description = "Swap Enter with Home or Build or Friend's house on Portal"
	)
	default HouseMode swapHomePortal()
	{
		return HouseMode.HOME;
	}

	@ConfigItem(
		keyName = "swapHouseAdvertisement",
		name = "House Advertisement",
		description = "Swap View with Add-House or Visit-Last on House Advertisement board"
	)
	default HouseAdvertisementMode swapHouseAdvertisement()
	{
		return HouseAdvertisementMode.VIEW;
	}

	@ConfigItem(
		keyName = "swapPickpocket",
		name = "Pickpocket",
		description = "Swap Talk-to with Pickpocket"
	)
	default boolean swapPickpocket()
	{
		return true;
	}

	@ConfigItem(
		keyName = "swapPay",
		name = "Pay",
		description = "Swap Talk-to with Pay on NPC<br>Example: Elstan, Heskel, Fayeth"
	)
	default boolean swapPay()
	{
		return true;
	}

	@ConfigItem(
		keyName = "swapJewelleryBox",
		name = "Jewellery Box",
		description = "Swap Teleport Menu with previous destination on Jewellery Box"
	)
	default boolean swapJewelleryBox()
	{
		return false;
	}

	@ConfigItem(
		keyName = "swapPrivate",
		name = "Private",
		description = "Swap Shared with Private on the Chambers of Xeric storage units."
	)
	default boolean swapPrivate()
	{
		return false;
	}

	@ConfigItem(
		keyName = "swapPick",
		name = "Pick",
		description = "Swap Pick with Pick-lots of the Gourd tree in the Chambers of Xeric"
	)
	default boolean swapPick()
	{
		return false;
	}

	@ConfigItem(
		keyName = "swapQuick",
		name = "Quick Pass/Open/Start/Travel",
		description = "Swap Pass with Quick-Pass, Open with Quick-Open, Ring with Quick-Start and Talk-to with Quick-Travel"
	)
	default boolean swapQuick()
	{
		return true;
	}

	@ConfigItem(
		keyName = "swapBoxTrap",
		name = "Reset",
		description = "Swap Check with Reset on box trap"
	)
	default boolean swapBoxTrap()
	{
		return true;
	}

	@ConfigItem(
		keyName = "swapTeleportItem",
		name = "Teleport item",
		description = "Swap Wear, Wield with Rub, Teleport on teleport item<br>Example: Amulet of glory, Explorer's ring, Chronicle"
	)
	default boolean swapTeleportItem()
	{
		return false;
	}

	@ConfigItem(
		keyName = "TeleFromEquipped",
		name = "Teleport from Equipped Screen",
		description = "Allows you to teleport from equipped items (used with Teleport Item)"
	)
	default boolean swapTeleportFromEquipped()
	{
		return false;
	}

	@ConfigItem(
		keyName = "swapAbyssTeleport",
		name = "Teleport to Abyss",
		description = "Swap Talk-to with Teleport for the Mage of Zamorak"
	)
	default boolean swapAbyssTeleport()
	{
		return true;
	}

	@ConfigItem(
		keyName = "swapTrade",
		name = "Trade",
		description = "Swap Talk-to with Trade on NPC<br>Example: Shop keeper, Shop assistant"
	)
	default boolean swapTrade()
	{
		return true;
	}

	@ConfigItem(
		keyName = "swapTravel",
		name = "Travel",
		description = "Swap Talk-to with Travel, Take-boat, Pay-fare, Charter on NPC<br>Example: Squire, Monk of Entrana, Customs officer, Trader Crewmember"
	)
	default boolean swapTravel()
	{
		return true;
	}

    @ConfigItem(
            keyName = "swapEnchant",
            name = "Enchant",
            description = "Swap Talk-to with Enchant for Eluned"
    )
    default boolean swapEnchant()
    {
        return true;
    }

	@ConfigItem(
		keyName = "swapDuelRing",
		name = "Swap Duel Arena",
		description = "One click duel arena",
		position = 25
	)
	default DuelRingMode swapDA()
	{
		return DuelRingMode.CASTLE_WARS;
	}

	@ConfigItem(
		keyName = "swapMaxCape",
		name = "Max Cape",
		description = "Swap tele option on max cape",
		position = 26
	)
	default MaxCapeMode swapMaxCape()
	{
		return MaxCapeMode.CRAFTING;
	}

	@ConfigItem(
		keyName = "swapDesert",
		name = "Desert Amulet",
		description = "Swap tele option on desert amulet",
		position = 27
	)
	default DesertAmuletMode swapDesert()
	{
		return DesertAmuletMode.NARDAH;
	}

	@ConfigItem(
		keyName = "swapArdy",
		name = "Ardourgne Cloak",
		description = "Swap tele option on Ardougne Cloak",
		position = 28
	)
	default ArdyCloakMode swapArdy()
	{
		return ArdyCloakMode.FARM;
	}

	@ConfigItem(
		keyName = "swapMory",
		name = "Morytania Legs",
		description = "Swap tele option on legs",
		position = 29
	)
	default MoryLegsMode swapMoryLegs()
	{
		return MoryLegsMode.BURGH;
	}

	@ConfigItem(
		keyName = "swapGloves",
		name = "Karamja Gloves",
		description = "Swap tele option on gloves",
		position = 30
	)
	default KaramGloveMode swapKaramGloves()
	{
		return KaramGloveMode.GEM;
	}

	@ConfigItem(
		keyName = "swapGlory",
		name = "Amulet of Glory",
		description = "Swap tele option on glory",
		position = 31
	)
	default GloryMode swapGlory()
	{
		return GloryMode.EDGE;
	}

	@ConfigItem(
		keyName = "swapXerics",
		name = "Xerics Talisman",
		description = "Swap tele option on xeric's talisman",
		position = 32
	)
	default XericsTalismanMode swapXerics()
	{
		return XericsTalismanMode.HONOUR;
	}

	@ConfigItem(
		keyName = "swapGames",
		name = "Games Necklace",
		description = "Swap tele option on Games Necklace",
		position = 33
	)
	default GamesNecklaceMode swapGames()
	{
		return GamesNecklaceMode.TEARS;
	}

	@ConfigItem(
		keyName = "swapBlessing",
		name = "Rada's Blessing",
		description = "Swap tele option on Rada's Blessing",
		position = 34
	)
	default BlessingMode swapBlessing()
	{
		return BlessingMode.MOUNT;
	}

	@ConfigItem(
		keyName = "swapDigsite",
		name = "Digsite Pendant",
		description = "Swap tele option on Digsite Pendant",
		position = 35
	)
	default DigsiteMode swapDigsite()
	{
		return DigsiteMode.ISLAND;
	}

	@ConfigItem(
		keyName = "swapFishingCape",
		name = "Fishing Cape",
		description = "Swap tele option on Fishing Cape",
		position = 36
	)
	default FishingCapeMode swapFishingCape()
	{
		return FishingCapeMode.GUILD;
	}

	@ConfigItem(
		keyName = "swapMemoirs",
		name = "Kharedst's Memoirs",
		description = "Swap tele option on Kharedst's Memoirs",
		position = 37
	)
	default MemoirsMode swapMemoirs()
	{
		return MemoirsMode.OFF;
	}

	@ConfigItem(
		keyName = "customSwap",
		name = "Custom menu swap",
		description = "Swap opt1|target1:opt2|target2, separated by commas",
		position = 38
	)
	default String customSwap() { return ""; }

	@ConfigItem(
		keyName = "valueSwap",
		name = "Store Value Swap",
		description = "Enable swapping value with buy/sell, items separated by commas",
		position = 39
	)
	default boolean enableValueSwap() { return false; }

	@ConfigItem(
		keyName = "buy1",
		name = "Buy/Sell 1",
		description = "Swap Value with Buy 1",
		position = 40
	)
	default String buy1() { return ""; }

	@ConfigItem(
		keyName = "buy5",
		name = "Buy/Sell 5",
		description = "Swap Value with Buy 5",
		position = 41
	)
	default String buy5() { return ""; }

	@ConfigItem(
		keyName = "buy10",
		name = "Buy/Sell 10",
		description = "Swap Value with Buy 10",
		position = 42
	)
	default String buy10() { return ""; }

	@ConfigItem(
		keyName = "buy50",
		name = "Buy/Sell 50",
		description = "Swap Value with Buy 50",
		position = 43
	)
	default String buy50() { return ""; }

	@ConfigItem(
		keyName = "bankingSwap",
		name = "Bank Swap",
		description = "Enable swapping Withdraw/Deposit in banks, items separated by commas",
		position = 44
	)
	default boolean enableBankingSwap() { return false; }

	@ConfigItem(
		keyName = "withdraw5",
		name = "Withdraw/Deposit 5",
		description = "Swap withdraw-1 with withdraw-5 / deposit-1 with deposit-5",
		position = 45
	)
	default String withdraw5() { return ""; }

	@ConfigItem(
		keyName = "withdraw10",
		name = "Withdraw/Deposit 10",
		description = "Swap withdraw-1 with withdraw-10 / deposit-1 with deposit-10",
		position = 46
	)
	default String withdraw10() { return ""; }

	@ConfigItem(
		keyName = "xAmount",
		name = "X Amount",
		description = "Select X amount for swapping",
		position = 47
	)
	default int xAmount() { return 1; }

	@ConfigItem(
		keyName = "withdrawX",
		name = "Withdraw/Deposit X",
		description = "Swap Withdraw-1 with Withdraw-X where X is the amount set above",
		position = 48
	)
	default String withdrawX() { return ""; }

	@ConfigItem(
		keyName = "withdrawAll",
		name = "Withdraw/Deposit All",
		description = "Swap Withdraw-1 with Withdraw-All",
		position = 49
	)
	default String withdrawAll() { return ""; }

	@ConfigItem(
		keyName = "drop",
		name = "Drop",
		description = "Swap use with drop for left click dropping",
		position = 50
	)
	default String dropItems() { return ""; }

	@ConfigItem(
		keyName = "swapTeleportSpell",
		name = "Shift-click teleport spells",
		description = "Swap teleport spells that have a second destination on shift"
	)
	default boolean swapTeleportSpell()
	{
		return false;
	}

	@ConfigItem(
		keyName = "swapStartMinigame",
		name = "Pyramid Plunder Start-minigame",
		description = "Swap Talk-to with Start-minigame at the Guardian Mummy"
	)
	default boolean swapStartMinigame()
	{
		return true;
	}

	@ConfigItem(
		keyName = "swapQuickleave",
		name = "Quick-Leave",
		description = "Swap Leave Tomb with Quick-Leave at Pyramid Plunder"
	)
	default boolean swapQuickLeave()
	{
		return false;
	}

	@ConfigItem(
		keyName = "swapGEItemCollect",
		name = "GE Item Collect",
		description = "Swap Collect-notes, Collect-items, or Bank options from GE offer"
	)
	default GEItemCollectMode swapGEItemCollect()
	{
		return GEItemCollectMode.DEFAULT;
	}

	@ConfigItem(
		keyName = "swapGEAbort",
		name = "GE Abort",
		description = "Swap abort offer on Grand Exchange offers when shift-clicking"
	)
	default boolean swapGEAbort()
	{
		return false;
	}

	@ConfigItem(
		keyName = "swapNpcContact",
		name = "NPC Contact",
		description = "Swap NPC Contact with last contacted NPC when shift-clicking"
	)
	default boolean swapNpcContact()
	{
		return false;
	}

	@ConfigItem(
		keyName = "bankWithdrawShiftClick",
		name = "Bank Withdraw Shift-Click",
		description = "Swaps the behavior of shift-click when withdrawing from bank."
	)
	default ShiftWithdrawMode bankWithdrawShiftClick()
	{
		return ShiftWithdrawMode.OFF;
	}

	@ConfigItem(
		keyName = "bankDepositShiftClick",
		name = "Bank Deposit Shift-Click",
		description = "Swaps the behavior of shift-click when depositing to bank."
	)
	default ShiftDepositMode bankDepositShiftClick()
	{
		return ShiftDepositMode.OFF;
	}

	@ConfigItem(
		keyName = "swapEssenceMineTeleport",
		name = "Essence Mine Teleport",
		description = "Swaps Talk-To with Teleport for NPCs which teleport you to the essence mine"
	)
	default boolean swapEssenceMineTeleport()
	{
		return false;
	}

	@ConfigItem(
		keyName = "swapNets",
		name = "Nets",
		description = "Swap Talk-to with Nets on Annette"
	)
	default boolean swapNets()
	{
		return true;
	}

	@ConfigItem(
		keyName = "swapGauntlet",
		name = "Corrupted Gauntlet",
		description = "Swap Enter with Enter-corrupted when entering The Gauntlet"
	)
	default boolean swapGauntlet()
	{
		return false;
	}
}
