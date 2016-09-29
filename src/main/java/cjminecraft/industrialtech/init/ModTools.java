package cjminecraft.industrialtech.init;

import cjminecraft.industrialtech.Reference;
import cjminecraft.industrialtech.items.ItemModAxe;
import cjminecraft.industrialtech.items.ItemModHoe;
import cjminecraft.industrialtech.items.ItemModPickaxe;
import cjminecraft.industrialtech.items.ItemModShovel;
import cjminecraft.industrialtech.items.ItemModSword;
import cjminecraft.industrialtech.items.ItemSoulStealer;
import cjminecraft.industrialtech.util.Utils;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModTools {
	
	//Tool Materials
	public static ToolMaterial copperMaterial = EnumHelper.addToolMaterial("copper", 2, 300, 5.0F, 2.0F, 12);
	public static ToolMaterial silverMaterial = EnumHelper.addToolMaterial("silver", 2, 300, 5.0F, 2.0F, 12);
	public static ToolMaterial tinMaterial = EnumHelper.addToolMaterial("tin", 2, 300, 5.0F, 2.0F, 12);
	public static ToolMaterial zincMaterial = EnumHelper.addToolMaterial("zinc", 2, 300, 5.0F, 2.0F, 12);
	public static ToolMaterial leadMaterial = EnumHelper.addToolMaterial("lead", 2, 300, 5.0F, 2.0F, 12);
	public static ToolMaterial nickelMaterial = EnumHelper.addToolMaterial("nickel", 2, 300, 5.0F, 2.0F, 12);
	
	//Pickaxes
	public static ItemPickaxe copperPickaxe;
	public static ItemPickaxe silverPickaxe;
	public static ItemPickaxe tinPickaxe;
	public static ItemPickaxe zincPickaxe;
	public static ItemPickaxe leadPickaxe;
	public static ItemPickaxe nickelPickaxe;
	
	//Axes
	public static ItemModAxe copperAxe;
	public static ItemModAxe silverAxe;
	public static ItemModAxe tinAxe;
	public static ItemModAxe zincAxe;
	public static ItemModAxe leadAxe;
	public static ItemModAxe nickelAxe;
	
	//Hoes
	public static ItemHoe copperHoe;
	public static ItemHoe silverHoe;
	public static ItemHoe tinHoe;
	public static ItemHoe zincHoe;
	public static ItemHoe leadHoe;
	public static ItemHoe nickelHoe;
	
	//Shovels
	public static ItemSpade copperShovel;
	public static ItemSpade silverShovel;
	public static ItemSpade tinShovel;
	public static ItemSpade zincShovel;
	public static ItemSpade leadShovel;
	public static ItemSpade nickelShovel;
	
	//Swords
	public static ItemSword copperSword;
	public static ItemSword silverSword;
	public static ItemSword tinSword;
	public static ItemSword zincSword;
	public static ItemSword leadSword;
	public static ItemSword nickelSword;
	
	public static Item soulStealer;
	
	public static void init() {
		//Pickaxes
		copperPickaxe = new ItemModPickaxe(copperMaterial, "copper_pickaxe");
		silverPickaxe = new ItemModPickaxe(silverMaterial, "silver_pickaxe");
		tinPickaxe = new ItemModPickaxe(tinMaterial, "tin_pickaxe");
		zincPickaxe = new ItemModPickaxe(zincMaterial, "zinc_pickaxe");
		leadPickaxe = new ItemModPickaxe(leadMaterial, "lead_pickaxe");
		nickelPickaxe = new ItemModPickaxe(nickelMaterial, "nickel_pickaxe");
		
		//Axe
		copperAxe = new ItemModAxe(copperMaterial, "copper_axe");
		silverAxe = new ItemModAxe(silverMaterial, "silver_axe");
		tinAxe = new ItemModAxe(tinMaterial, "tin_axe");
		zincAxe = new ItemModAxe(zincMaterial, "zinc_axe");
		leadAxe = new ItemModAxe(leadMaterial, "lead_axe");
		nickelAxe = new ItemModAxe(nickelMaterial, "nickel_axe");
		
		//Hoe
		copperHoe = new ItemModHoe(copperMaterial, "copper_hoe");
		silverHoe = new ItemModHoe(silverMaterial, "silver_hoe");
		tinHoe = new ItemModHoe(tinMaterial, "tin_hoe");
		zincHoe = new ItemModHoe(zincMaterial, "zinc_hoe");
		leadHoe = new ItemModHoe(leadMaterial, "lead_hoe");
		nickelHoe = new ItemModHoe(nickelMaterial, "nickel_hoe");
		
		//Shovel
		copperShovel = new ItemModShovel(copperMaterial, "copper_shovel");
		silverShovel = new ItemModShovel(silverMaterial, "silver_shovel");
		tinShovel = new ItemModShovel(tinMaterial, "tin_shovel");
		zincShovel = new ItemModShovel(zincMaterial, "zinc_shovel");
		leadShovel = new ItemModShovel(leadMaterial, "lead_shovel");
		nickelShovel = new ItemModShovel(nickelMaterial, "nickel_shovel");
		
		//Sword
		copperSword = new ItemModSword(copperMaterial, "copper_sword");
		silverSword = new ItemModSword(silverMaterial, "silver_sword");
		tinSword = new ItemModSword(tinMaterial, "tin_sword");
		zincSword = new ItemModSword(zincMaterial, "zinc_sword");
		leadSword = new ItemModSword(leadMaterial, "lead_sword");
		nickelSword = new ItemModSword(nickelMaterial, "nickel_sword");
		
		soulStealer = new ItemSoulStealer("soul_stealer");
	}
	
	public static void register() {
		//Pickaxe
		registerItem(copperPickaxe);
		registerItem(silverPickaxe);
		registerItem(tinPickaxe);
		registerItem(zincPickaxe);
		registerItem(leadPickaxe);
		registerItem(nickelPickaxe);
		
		//Axe
		registerItem(copperAxe);
		registerItem(silverAxe);
		registerItem(tinAxe);
		registerItem(zincAxe);
		registerItem(leadAxe);
		registerItem(nickelAxe);
		
		//Hoe
		registerItem(copperHoe);
		registerItem(silverHoe);
		registerItem(tinHoe);
		registerItem(zincHoe);
		registerItem(leadHoe);
		registerItem(nickelHoe);
		
		//Shovel
		registerItem(copperShovel);
		registerItem(silverShovel);
		registerItem(tinShovel);
		registerItem(zincShovel);
		registerItem(leadShovel);
		registerItem(nickelShovel);
		
		//Sword
		registerItem(copperSword);
		registerItem(silverSword);
		registerItem(tinSword);
		registerItem(zincSword);
		registerItem(leadSword);
		registerItem(nickelSword);
		
		registerItem(soulStealer);
		
	}
	
	public static void registerRenders() {
		//Pickaxe
		registerRender(copperPickaxe);
		registerRender(silverPickaxe);
		registerRender(tinPickaxe);
		registerRender(zincPickaxe);
		registerRender(leadPickaxe);
		registerRender(nickelPickaxe);
		
		//Axe
		registerRender(copperAxe);
		registerRender(silverAxe);
		registerRender(tinAxe);
		registerRender(zincAxe);
		registerRender(leadAxe);
		registerRender(nickelAxe);
		
		//Hoe
		registerRender(copperHoe);
		registerRender(silverHoe);
		registerRender(tinHoe);
		registerRender(zincHoe);
		registerRender(leadHoe);
		registerRender(nickelHoe);
		
		//Shovel
		registerRender(copperShovel);
		registerRender(silverShovel);
		registerRender(tinShovel);
		registerRender(zincShovel);
		registerRender(leadShovel);
		registerRender(nickelShovel);
		
		//Sword
		registerRender(copperSword);
		registerRender(silverSword);
		registerRender(tinSword);
		registerRender(zincSword);
		registerRender(leadSword);
		registerRender(nickelSword);
		
		registerRender(soulStealer);
		
	}
	
	public static void registerItem(Item item) {
		GameRegistry.register(item);
		Utils.getLogger().info("Registered Item: " + item.getUnlocalizedName().substring(5));
	}
	
	public static void registerRender(Item item) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(new ResourceLocation(Reference.MODID, item.getUnlocalizedName().substring(5)), "inventory"));
		Utils.getLogger().info("Registered Render For Item: " + item.getUnlocalizedName().substring(5));
	}

}
