package cjminecraft.industrialtech.handlers;

import java.util.ArrayList;
import java.util.List;

import cjminecraft.industrialtech.IndustrialTech;
import cjminecraft.industrialtech.Reference;
import cjminecraft.industrialtech.init.ModArmour;
import cjminecraft.industrialtech.init.ModBlocks;
import cjminecraft.industrialtech.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

public class AchievementHandler {
	
	private static List<Achievement> achievements = new ArrayList<Achievement>();
	
	public static Achievement achievementFirstJoin = createAchievement("firstjoin", 0, 0, ModItems.chip);
	public static Achievement achievementTinMan = createAchievement("tinman", 2, 0, ModArmour.tinHelmet);
	public static Achievement achievementGotThePower = createAchievement("gotthepower", 2, 0, ModBlocks.generator);
	
	public static void registerAchievements() {
		Achievement[] achievementArray = new Achievement[achievements.size()];
		for(Achievement achievement : achievements) {
			achievement.registerStat();
			achievementArray[achievements.indexOf(achievement)] = achievement;
		}
		
		AchievementPage.registerAchievementPage(new AchievementPage(Reference.NAME, achievementArray));
	}
	
	private static Achievement createAchievement(String name, int column, int row, Item item) {
		Achievement achievement = new Achievement("achievement." + name, name, column, row, item, (Achievement) null);
		achievements.add(achievement);
		return achievement;
	}
	
	private static Achievement createAchievement(String name, int column, int row, Block block) {
		Achievement achievement = new Achievement("achievement." + name, name, column, row, block, (Achievement) null);
		achievements.add(achievement);
		return achievement;
	}
	
	private static Achievement createAchievement(String name, int column, int row, ItemStack stack) {
		Achievement achievement = new Achievement("achievement." + name, name, column, row, stack, (Achievement) null);
		achievements.add(achievement);
		return achievement;
	}

}
