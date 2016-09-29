package cjminecraft.industrialtech.events;

import cjminecraft.industrialtech.handlers.AchievementHandler;
import cjminecraft.industrialtech.init.ModBlocks;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;

public class CraftingEvents {
	
	@SubscribeEvent
	public void onEvent(ItemCraftedEvent event) {
		if(event.crafting.getItem() == Item.getItemFromBlock(ModBlocks.generator) && !event.player.hasAchievement(AchievementHandler.achievementGotThePower)) {
			event.player.addStat(AchievementHandler.achievementGotThePower);
		}
	}

}
