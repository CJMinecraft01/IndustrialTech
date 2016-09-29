package cjminecraft.industrialtech.init;

import cjminecraft.industrialtech.Reference;
import cjminecraft.industrialtech.handlers.EnumHandler;
import cjminecraft.industrialtech.items.ItemChip;
import cjminecraft.industrialtech.items.ItemHeart;
import cjminecraft.industrialtech.items.ItemIngot;
import cjminecraft.industrialtech.util.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems {
	
	//Basics Update 0.1.0
	public static Item ingot;
	public static Item nugget;
	
	//Redstone Flux Update 0.3.0
	public static Item chip;
	
	public static Item heart;
	
	public static void init() {
		ingot = new ItemIngot("ingot");
		nugget = new ItemIngot("nugget");
		
		heart = new ItemHeart("heart");
		
		chip = new ItemChip("chip");
	}
	
	public static void register() {
		registerItem(ingot);
		registerItem(nugget);
		
		registerItem(heart);
		
		registerItem(chip);
	}
	
	public static void registerRenders() {
		registerRenderIngots();
	}
	
	private static void registerRenderIngots() {
		for(int i = 0; i < EnumHandler.IngotTypes.values().length; i++) {
			registerRender(ingot, i, EnumHandler.IngotTypes.values()[i].getName() + "_ingot");
			registerRender(nugget, i, EnumHandler.IngotTypes.values()[i].getName() + "_nugget");
		}
		registerRender(heart);
		for(int i = 0; i < EnumHandler.ChipTypes.values().length; i++) {
			registerRender(chip, i, "chip_" + EnumHandler.ChipTypes.values()[i].getName());
		}
	}
	
	public static void registerItem(Item item) {
		GameRegistry.register(item);
		Utils.getLogger().info("Registered Item: " + item.getUnlocalizedName().substring(5));
	}
	
	public static void registerRender(Item item) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(new ResourceLocation(Reference.MODID, item.getUnlocalizedName().substring(5)), "inventory"));
		Utils.getLogger().info("Registered Render For Item: " + item.getUnlocalizedName().substring(5));
	}
	
	public static void registerRender(Item item, int meta) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(new ResourceLocation(Reference.MODID, item.getUnlocalizedName().substring(5)), "inventory"));
		Utils.getLogger().info("Registered Render For Item: " + item.getUnlocalizedName().substring(5));
	}
	
	public static void registerRender(Item item, String fileName) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(new ResourceLocation(Reference.MODID, fileName), "inventory"));
		Utils.getLogger().info("Registered Render For Item: " + item.getUnlocalizedName().substring(5));
	}
	
	public static void registerRender(Item item, int meta, String fileName) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(new ResourceLocation(Reference.MODID, fileName), "inventory"));
		Utils.getLogger().info("Registered Render For Item: " + item.getUnlocalizedName().substring(5));
	}

}
