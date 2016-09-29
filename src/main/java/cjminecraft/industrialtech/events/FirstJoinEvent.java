package cjminecraft.industrialtech.events;

import cjminecraft.industrialtech.handlers.AchievementHandler;
import cjminecraft.industrialtech.util.UpdateChecker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class FirstJoinEvent {
	
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onEvent(EntityJoinWorldEvent event) {
		if(event.getEntity() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.getEntity();
			if(!player.hasAchievement(AchievementHandler.achievementFirstJoin)) {
				player.addStat(AchievementHandler.achievementFirstJoin);
			}
			UpdateChecker.checkForUpdates(player);
		}
	}

}
