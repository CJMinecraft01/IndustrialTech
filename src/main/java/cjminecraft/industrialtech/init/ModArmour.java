package cjminecraft.industrialtech.init;

import cjminecraft.industrialtech.Reference;
import cjminecraft.industrialtech.items.ItemModArmour;
import cjminecraft.industrialtech.util.Utils;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModArmour {
	
	//Armour Materials
	public static ArmorMaterial copperMaterial = EnumHelper.addArmorMaterial("copper", "industrialtech:copper", 15, new int[]{2,6,5,2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2.0F);
	public static ArmorMaterial silverMaterial = EnumHelper.addArmorMaterial("silver", "industrialtech:silver", 15, new int[]{2,6,5,2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2.0F);
	public static ArmorMaterial tinMaterial = EnumHelper.addArmorMaterial("tin", "industrialtech:tin", 15, new int[]{2,6,5,2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2.0F);
	public static ArmorMaterial zincMaterial = EnumHelper.addArmorMaterial("zinc", "industrialtech:zinc", 15, new int[]{2,6,5,2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2.0F);
	public static ArmorMaterial leadMaterial = EnumHelper.addArmorMaterial("lead", "industrialtech:lead", 15, new int[]{2,6,5,2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2.0F);
	public static ArmorMaterial nickelMaterial = EnumHelper.addArmorMaterial("nickel", "industrialtech:nickel", 15, new int[]{2,6,5,2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2.0F);
	
	//Copper
	public static ItemArmor copperHelmet;
	public static ItemArmor copperChestplate;
	public static ItemArmor copperLeggings;
	public static ItemArmor copperBoots;
	
	//Silver
	public static ItemArmor silverHelmet;
	public static ItemArmor silverChestplate;
	public static ItemArmor silverLeggings;
	public static ItemArmor silverBoots;
	
	//Tin
	public static ItemArmor tinHelmet;
	public static ItemArmor tinChestplate;
	public static ItemArmor tinLeggings;
	public static ItemArmor tinBoots;
	
	//Zinc
	public static ItemArmor zincHelmet;
	public static ItemArmor zincChestplate;
	public static ItemArmor zincLeggings;
	public static ItemArmor zincBoots;
	
	//Lead
	public static ItemArmor leadHelmet;
	public static ItemArmor leadChestplate;
	public static ItemArmor leadLeggings;
	public static ItemArmor leadBoots;
	
	//Nickel
	public static ItemArmor nickelHelmet;
	public static ItemArmor nickelChestplate;
	public static ItemArmor nickelLeggings;
	public static ItemArmor nickelBoots;
	
	public static void init() {
		//Copper
		copperHelmet = new ItemModArmour(copperMaterial, 1, EntityEquipmentSlot.HEAD, "copper_helmet", "copper_helmet");
		copperChestplate = new ItemModArmour(copperMaterial, 1, EntityEquipmentSlot.CHEST, "copper_chestplate", "copper_chestplate");
		copperLeggings = new ItemModArmour(copperMaterial, 2, EntityEquipmentSlot.LEGS, "copper_leggings", "copper_leggings");
		copperBoots = new ItemModArmour(copperMaterial, 1, EntityEquipmentSlot.FEET, "copper_boots", "copper_boots");
		
		//Silver
		silverHelmet = new ItemModArmour(silverMaterial, 1, EntityEquipmentSlot.HEAD, "silver_helmet", "silver_helmet");
		silverChestplate = new ItemModArmour(silverMaterial, 1, EntityEquipmentSlot.CHEST, "silver_chestplate", "silver_chestplate");
		silverLeggings = new ItemModArmour(silverMaterial, 2, EntityEquipmentSlot.LEGS, "silver_leggings", "silver_leggings");
		silverBoots = new ItemModArmour(silverMaterial, 1, EntityEquipmentSlot.FEET, "silver_boots", "silver_boots");
		
		//Tin
		tinHelmet = new ItemModArmour(tinMaterial, 1, EntityEquipmentSlot.HEAD, "tin_helmet", "tin_helmet");
		tinChestplate = new ItemModArmour(tinMaterial, 1, EntityEquipmentSlot.CHEST, "tin_chestplate", "tin_chestplate");
		tinLeggings = new ItemModArmour(tinMaterial, 2, EntityEquipmentSlot.LEGS, "tin_leggings", "tin_leggings");
		tinBoots = new ItemModArmour(tinMaterial, 1, EntityEquipmentSlot.FEET, "tin_boots", "tin_boots");
		
		//Zinc
		zincHelmet = new ItemModArmour(zincMaterial, 1, EntityEquipmentSlot.HEAD, "zinc_helmet", "zinc_helmet");
		zincChestplate = new ItemModArmour(zincMaterial, 1, EntityEquipmentSlot.CHEST, "zinc_chestplate", "zinc_chestplate");
		zincLeggings = new ItemModArmour(zincMaterial, 2, EntityEquipmentSlot.LEGS, "zinc_leggings", "zinc_leggings");
		zincBoots = new ItemModArmour(zincMaterial, 1, EntityEquipmentSlot.FEET, "zinc_boots", "zinc_boots");
		
		//Lead
		leadHelmet = new ItemModArmour(leadMaterial, 1, EntityEquipmentSlot.HEAD, "lead_helmet", "lead_helmet");
		leadChestplate = new ItemModArmour(leadMaterial, 1, EntityEquipmentSlot.CHEST, "lead_chestplate", "lead_chestplate");
		leadLeggings = new ItemModArmour(leadMaterial, 2, EntityEquipmentSlot.LEGS, "lead_leggings", "lead_leggings");
		leadBoots = new ItemModArmour(leadMaterial, 1, EntityEquipmentSlot.FEET, "lead_boots", "lead_boots");
		
		//Nickel
		nickelHelmet = new ItemModArmour(nickelMaterial, 1, EntityEquipmentSlot.HEAD, "nickel_helmet", "nickel_helmet");
		nickelChestplate = new ItemModArmour(nickelMaterial, 1, EntityEquipmentSlot.CHEST, "nickel_chestplate", "nickel_chestplate");
		nickelLeggings = new ItemModArmour(nickelMaterial, 2, EntityEquipmentSlot.LEGS, "nickel_leggings", "nickel_leggings");
		nickelBoots = new ItemModArmour(nickelMaterial, 1, EntityEquipmentSlot.FEET, "nickel_boots", "nickel_boots");
	}
	
	public static void register() {
		//Copper
		registerItem(copperHelmet);
		registerItem(copperChestplate);
		registerItem(copperLeggings);
		registerItem(copperBoots);
		
		//Silver
		registerItem(silverHelmet);
		registerItem(silverChestplate);
		registerItem(silverLeggings);
		registerItem(silverBoots);
		
		//Tin
		registerItem(tinHelmet);
		registerItem(tinChestplate);
		registerItem(tinLeggings);
		registerItem(tinBoots);
		
		//Zinc
		registerItem(zincHelmet);
		registerItem(zincChestplate);
		registerItem(zincLeggings);
		registerItem(zincBoots);
		
		//Lead
		registerItem(leadHelmet);
		registerItem(leadChestplate);
		registerItem(leadLeggings);
		registerItem(leadBoots);
		
		//Nickel
		registerItem(nickelHelmet);
		registerItem(nickelChestplate);
		registerItem(nickelLeggings);
		registerItem(nickelBoots);
	}
	
	public static void registerRenders() {
		registerRender(copperHelmet);
		registerRender(copperChestplate);
		registerRender(copperLeggings);
		registerRender(copperBoots);
		
		registerRender(silverHelmet);
		registerRender(silverChestplate);
		registerRender(silverLeggings);
		registerRender(silverBoots);
		
		registerRender(tinHelmet);
		registerRender(tinChestplate);
		registerRender(tinLeggings);
		registerRender(tinBoots);
		
		registerRender(zincHelmet);
		registerRender(zincChestplate);
		registerRender(zincLeggings);
		registerRender(zincBoots);
		
		registerRender(leadHelmet);
		registerRender(leadChestplate);
		registerRender(leadLeggings);
		registerRender(leadBoots);
		
		registerRender(nickelHelmet);
		registerRender(nickelChestplate);
		registerRender(nickelLeggings);
		registerRender(nickelBoots);
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
