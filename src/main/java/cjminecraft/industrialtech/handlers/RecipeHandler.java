package cjminecraft.industrialtech.handlers;

import cjminecraft.industrialtech.blocks.BlockOre;
import cjminecraft.industrialtech.init.ModArmour;
import cjminecraft.industrialtech.init.ModBlocks;
import cjminecraft.industrialtech.init.ModItems;
import cjminecraft.industrialtech.init.ModTools;
import cjminecraft.industrialtech.util.Utils;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class RecipeHandler {
	
	public static void registerCraftingRecipes() {
		registerNuggets();
		registerCompressedBlocks();
		registerToolsAndArmour();
		
		GameRegistry.addRecipe(new ItemStack(ModTools.soulStealer), new Object[] {"WSW", "SDS", "WSW", 'W', Items.STICK, 'S', Items.STRING, 'D', Items.DIAMOND});
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.miniChest, 9), new Object[] {Blocks.CHEST});
		GameRegistry.addRecipe(new ItemStack(Blocks.CHEST), new Object[] {"CCC", "CCC", "CCC", 'C', ModBlocks.miniChest});
		
		GameRegistry.addRecipe(new ItemStack(ModItems.chip, 1, 0), new Object[] { "IRI", "RGR", "IRI", 'I', Items.IRON_INGOT, 'R', Items.REDSTONE, 'G', new ItemStack(Items.DYE, 1, EnumDyeColor.GREEN.getDyeDamage()) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.generatorFrame), new Object[] { "ZTZ", "TCT", "ZFZ", 'Z', new ItemStack(ModItems.ingot, 1, 3), 'T', new ItemStack(ModItems.ingot, 1, 2), 'C', new ItemStack(ModItems.chip, 1, 0), 'F', Blocks.FURNACE });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.generator, 1, 0), new Object[] { " I ", "CFC", "RBR", 'C', new ItemStack(ModItems.ingot, 1, 0), 'F', ModBlocks.generatorFrame, 'B', Blocks.FURNACE, 'I', Blocks.IRON_BLOCK, 'R', Items.REDSTONE });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.generator, 1, 1), new Object[] { " I ", "CFC", "RBR", 'C', new ItemStack(ModItems.ingot, 1, 0), 'F', ModBlocks.generatorFrame, 'B', Items.CAKE, 'I', Blocks.IRON_BLOCK, 'R', Items.REDSTONE });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.generator, 1, 2), new Object[] { " I ", "CFC", "RBR", 'C', new ItemStack(ModItems.ingot, 1, 0), 'F', ModBlocks.generatorFrame, 'B', Blocks.REDSTONE_BLOCK, 'I', Blocks.IRON_BLOCK, 'R', Items.REDSTONE });
		
		Utils.getLogger().info("Registered Crafting Recipes!");
	}
	
	public static void registerFurnaceRecipes() {
		registerOreRecipe(ModItems.ingot, ModBlocks.ore, 0, BlockOre.OreType.COPPER);
		registerOreRecipe(ModItems.ingot, ModBlocks.ore, 1, BlockOre.OreType.SILVER);
		registerOreRecipe(ModItems.ingot, ModBlocks.ore, 2, BlockOre.OreType.TIN);
		registerOreRecipe(ModItems.ingot, ModBlocks.ore, 3, BlockOre.OreType.ZINC);
		registerOreRecipe(ModItems.ingot, ModBlocks.ore, 4, BlockOre.OreType.LEAD);
		registerOreRecipe(ModItems.ingot, ModBlocks.ore, 5, BlockOre.OreType.NICKEL);
		
		Utils.getLogger().info("Registered Furnace Recipes!");
	}
	
	public static void registerNuggets() {
		for(int i = 0; i < EnumHandler.IngotTypes.values().length; i++) {
			GameRegistry.addShapelessRecipe(new ItemStack(ModItems.nugget, 9, i), new Object[] {new ItemStack(ModItems.ingot, 1, i)});
			GameRegistry.addRecipe(new ItemStack(ModItems.ingot, 1, i), new Object[] {"NNN","NNN","NNN",'N',new ItemStack(ModItems.nugget, 1, i)});
		}
	}
	
	public static void registerToolsAndArmour() {
		ItemStack ingot = new ItemStack(ModItems.ingot);
		ingot.setItemDamage(0);
		registerTools(ModTools.copperPickaxe, ModTools.copperAxe, ModTools.copperHoe, ModTools.copperShovel, ModTools.copperSword, ingot);
		registerArmour(ModArmour.copperHelmet, ModArmour.copperChestplate, ModArmour.copperLeggings, ModArmour.copperBoots, ingot);
		ingot.setItemDamage(1);
		registerTools(ModTools.silverPickaxe, ModTools.silverAxe, ModTools.silverHoe, ModTools.silverShovel, ModTools.silverSword, ingot);
		registerArmour(ModArmour.silverHelmet, ModArmour.silverChestplate, ModArmour.silverLeggings, ModArmour.silverBoots, ingot);
		ingot.setItemDamage(2);
		registerTools(ModTools.tinPickaxe, ModTools.tinAxe, ModTools.tinHoe, ModTools.tinShovel, ModTools.tinSword, ingot);
		registerArmour(ModArmour.tinHelmet, ModArmour.tinChestplate, ModArmour.tinLeggings, ModArmour.tinBoots, ingot);
		ingot.setItemDamage(3);
		registerTools(ModTools.zincPickaxe, ModTools.zincAxe, ModTools.zincHoe, ModTools.zincShovel, ModTools.zincSword, ingot);
		registerArmour(ModArmour.zincHelmet, ModArmour.zincChestplate, ModArmour.zincLeggings, ModArmour.zincBoots, ingot);
		ingot.setItemDamage(4);
		registerTools(ModTools.leadPickaxe, ModTools.leadAxe, ModTools.leadHoe, ModTools.leadShovel, ModTools.leadSword, ingot);
		registerArmour(ModArmour.leadHelmet, ModArmour.leadChestplate, ModArmour.leadLeggings, ModArmour.leadBoots, ingot);
		ingot.setItemDamage(5);
		registerTools(ModTools.nickelPickaxe, ModTools.nickelAxe, ModTools.nickelHoe, ModTools.nickelShovel, ModTools.nickelSword, ingot);
		registerArmour(ModArmour.nickelHelmet, ModArmour.nickelChestplate, ModArmour.nickelLeggings, ModArmour.nickelBoots, ingot);
		ingot.setItemDamage(10);
	}
	
	public static void registerCompressedBlocks() {
		for(int i = 0; i < EnumHandler.IngotTypes.values().length; i++) {
			GameRegistry.addRecipe(new ItemStack(Item.getItemFromBlock(ModBlocks.metalCompressed), 1, i), new Object[] { "III","III","III", 'I', new ItemStack(ModItems.ingot, 1, i) });
			GameRegistry.addShapelessRecipe(new ItemStack(ModItems.ingot, 9, i), new Object[] {new ItemStack(ModBlocks.metalCompressed,1,i)});
		}
		GameRegistry.addRecipe(new ItemStack(ModBlocks.cobbleCompressed, 1, 0), new Object[] {"CCC","CCC","CCC", 'C', Blocks.COBBLESTONE});
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.COBBLESTONE, 9), new Object[] { new ItemStack(ModBlocks.cobbleCompressed, 1, 0) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.graniteCompressed, 1, 0), new Object[] {"CCC","CCC","CCC", 'C', new ItemStack(Blocks.STONE, 1, 1)});
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.STONE, 9, 1), new Object[] { new ItemStack(ModBlocks.graniteCompressed, 1, 0) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.dioriteCompressed, 1, 0), new Object[] {"CCC","CCC","CCC", 'C', new ItemStack(Blocks.STONE, 1, 3)});
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.STONE, 9, 3), new Object[] { new ItemStack(ModBlocks.dioriteCompressed, 1, 0) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.andesiteCompressed, 1, 0), new Object[] {"CCC","CCC","CCC", 'C', new ItemStack(Blocks.STONE, 1, 5)});
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.STONE, 9, 5), new Object[] { new ItemStack(ModBlocks.andesiteCompressed, 1, 0) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.dirtCompressed, 1, 0), new Object[] {"CCC","CCC","CCC", 'C', Blocks.DIRT});
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.DIRT, 9), new Object[] { new ItemStack(ModBlocks.dirtCompressed, 1, 0) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.sandCompressed, 1, 0), new Object[] {"CCC","CCC","CCC", 'C', Blocks.SAND});
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.SAND, 9), new Object[] { new ItemStack(ModBlocks.sandCompressed, 1, 0) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.gravelCompressed, 1, 0), new Object[] {"CCC","CCC","CCC", 'C', Blocks.GRAVEL});
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.GRAVEL, 9), new Object[] { new ItemStack(ModBlocks.gravelCompressed, 1, 0) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.netherrackCompressed, 1, 0), new Object[] {"CCC","CCC","CCC", 'C', Blocks.NETHERRACK});
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.NETHERRACK, 9), new Object[] { new ItemStack(ModBlocks.netherrackCompressed, 1, 0) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.soulsandCompressed, 1, 0), new Object[] {"CCC","CCC","CCC", 'C', Blocks.SOUL_SAND});
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.SOUL_SAND, 9), new Object[] { new ItemStack(ModBlocks.soulsandCompressed, 1, 0) });
		for(int i = 1; i < EnumHandler.CompressedTypes.values().length; i++) {
			GameRegistry.addRecipe(new ItemStack(ModBlocks.cobbleCompressed, 1, i), new Object[] {"CCC","CCC","CCC", 'C', new ItemStack(ModBlocks.cobbleCompressed, 1, i - 1)});
			GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.cobbleCompressed, 9, i - 1), new Object[] { new ItemStack(ModBlocks.cobbleCompressed, 1, i) });
			GameRegistry.addRecipe(new ItemStack(ModBlocks.graniteCompressed, 1, i), new Object[] {"CCC","CCC","CCC", 'C', new ItemStack(ModBlocks.graniteCompressed, 1, i - 1)});
			GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.graniteCompressed, 9, i - 1), new Object[] { new ItemStack(ModBlocks.graniteCompressed, 1, i) });
			GameRegistry.addRecipe(new ItemStack(ModBlocks.dioriteCompressed, 1, i), new Object[] {"CCC","CCC","CCC", 'C', new ItemStack(ModBlocks.dioriteCompressed, 1, i - 1)});
			GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.dioriteCompressed, 9, i - 1), new Object[] { new ItemStack(ModBlocks.dioriteCompressed, 1, i) });
			GameRegistry.addRecipe(new ItemStack(ModBlocks.andesiteCompressed, 1, i), new Object[] {"CCC","CCC","CCC", 'C', new ItemStack(ModBlocks.andesiteCompressed, 1, i - 1)});
			GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.andesiteCompressed, 9, i - 1), new Object[] { new ItemStack(ModBlocks.andesiteCompressed, 1, i) });
			GameRegistry.addRecipe(new ItemStack(ModBlocks.dirtCompressed, 1, i), new Object[] {"CCC","CCC","CCC", 'C', new ItemStack(ModBlocks.dirtCompressed, 1, i - 1)});
			GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.dirtCompressed, 9, i - 1), new Object[] { new ItemStack(ModBlocks.dirtCompressed, 1, i) });
			GameRegistry.addRecipe(new ItemStack(ModBlocks.sandCompressed, 1, i), new Object[] {"CCC","CCC","CCC", 'C', new ItemStack(ModBlocks.sandCompressed, 1, i - 1)});
			GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.sandCompressed, 9, i - 1), new Object[] { new ItemStack(ModBlocks.sandCompressed, 1, i) });
			GameRegistry.addRecipe(new ItemStack(ModBlocks.gravelCompressed, 1, i), new Object[] {"CCC","CCC","CCC", 'C', new ItemStack(ModBlocks.gravelCompressed, 1, i - 1)});
			GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.gravelCompressed, 9, i - 1), new Object[] { new ItemStack(ModBlocks.gravelCompressed, 1, i) });
			GameRegistry.addRecipe(new ItemStack(ModBlocks.netherrackCompressed, 1, i), new Object[] {"CCC","CCC","CCC", 'C', new ItemStack(ModBlocks.netherrackCompressed, 1, i - 1)});
			GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.netherrackCompressed, 9, i - 1), new Object[] { new ItemStack(ModBlocks.netherrackCompressed, 1, i) });
			GameRegistry.addRecipe(new ItemStack(ModBlocks.soulsandCompressed, 1, i), new Object[] {"CCC","CCC","CCC", 'C', new ItemStack(ModBlocks.soulsandCompressed, 1, i - 1)});
			GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.soulsandCompressed, 9, i - 1), new Object[] { new ItemStack(ModBlocks.soulsandCompressed, 1, i) });
		}
	}
	
	public static void registerOreRecipe(Item output, Block input, int meta, BlockOre.OreType type) {
		GameRegistry.addSmelting(new ItemStack(input, 1, type.getID()), new ItemStack(output, 1, meta), 1);
	}
	
	public static void registerFurnaceRecipe(Item output, Item input, float xp) {
		GameRegistry.addSmelting(input, new ItemStack(output), xp);
	}
	
	public static void registerFurnaceRecipe(Block output, Item input, float xp) {
		GameRegistry.addSmelting(input, new ItemStack(output), xp);
	}
	
	public static void registerTools(Item pickaxe, Item axe, Item hoe, Item shovel, Item sword, ItemStack ingot) {
		GameRegistry.addRecipe(new ItemStack(pickaxe), new Object[] {"III"," S "," S ", 'I', ingot, 'S', Items.STICK });
		GameRegistry.addRecipe(new ItemStack(axe), new Object[] {" II"," SI"," S ", 'I', ingot, 'S', Items.STICK });
		GameRegistry.addRecipe(new ItemStack(axe), new Object[] {"II ","IS "," S ", 'I', ingot, 'S', Items.STICK });
		GameRegistry.addRecipe(new ItemStack(hoe), new Object[] {"II "," S "," S ", 'I', ingot, 'S', Items.STICK });
		GameRegistry.addRecipe(new ItemStack(hoe), new Object[] {" II"," S "," S ", 'I', ingot, 'S', Items.STICK });
		GameRegistry.addRecipe(new ItemStack(shovel), new Object[] {" I "," S "," S ", 'I', ingot, 'S', Items.STICK });
		GameRegistry.addRecipe(new ItemStack(shovel), new Object[] {"I  ","S  ","S  ", 'I', ingot, 'S', Items.STICK });
		GameRegistry.addRecipe(new ItemStack(shovel), new Object[] {"  I","  S","  S", 'I', ingot, 'S', Items.STICK });
		GameRegistry.addRecipe(new ItemStack(sword), new Object[] {" I "," I "," S ", 'I', ingot, 'S', Items.STICK });
		GameRegistry.addRecipe(new ItemStack(sword), new Object[] {"I  ","I  ","S  ", 'I', ingot, 'S', Items.STICK });
		GameRegistry.addRecipe(new ItemStack(sword), new Object[] {"  I","  I","  S", 'I', ingot, 'S', Items.STICK });
	}
	
	public static void registerArmour(Item helmet, Item chestplate, Item leggings, Item boots, ItemStack ingot) {
		GameRegistry.addRecipe(new ItemStack(helmet), new Object[] { "III","I I","   ",'I',ingot});
		GameRegistry.addRecipe(new ItemStack(helmet), new Object[] { "   ","III","I I",'I',ingot});
		GameRegistry.addRecipe(new ItemStack(chestplate), new Object[] { "I I","III","III",'I',ingot});
		GameRegistry.addRecipe(new ItemStack(leggings), new Object[] { "III","I I","I I",'I',ingot});
		GameRegistry.addRecipe(new ItemStack(boots), new Object[] { "I I","I I","   ",'I',ingot});
		GameRegistry.addRecipe(new ItemStack(boots), new Object[] { "   ","I I","I I",'I',ingot});
	}

}
