package cjminecraft.industrialtech.handlers;

import cjminecraft.industrialtech.blocks.BlockOre;
import cjminecraft.industrialtech.init.ModBlocks;
import cjminecraft.industrialtech.init.ModItems;
import cjminecraft.industrialtech.init.ModTools;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictHandler {
	
	public static void registerOreDictionary() {
		OreDictionary.registerOre("oreCopper", ModBlocks.ore.getDefaultState().withProperty(BlockOre.TYPE, BlockOre.OreType.COPPER).getBlock());
		OreDictionary.registerOre("oreSilver", ModBlocks.ore.getDefaultState().withProperty(BlockOre.TYPE, BlockOre.OreType.SILVER).getBlock());
		OreDictionary.registerOre("oreTin", ModBlocks.ore.getDefaultState().withProperty(BlockOre.TYPE, BlockOre.OreType.TIN).getBlock());
		OreDictionary.registerOre("oreZinc", ModBlocks.ore.getDefaultState().withProperty(BlockOre.TYPE, BlockOre.OreType.ZINC).getBlock());
		OreDictionary.registerOre("oreLead", ModBlocks.ore.getDefaultState().withProperty(BlockOre.TYPE, BlockOre.OreType.LEAD).getBlock());
		OreDictionary.registerOre("oreNickel", ModBlocks.ore.getDefaultState().withProperty(BlockOre.TYPE, BlockOre.OreType.NICKEL).getBlock());
		
		OreDictionary.registerOre("ingotCopper", new ItemStack(ModItems.ingot, 1, 0));
		OreDictionary.registerOre("ingotSilver", new ItemStack(ModItems.ingot, 1, 1));
		OreDictionary.registerOre("ingotTin", new ItemStack(ModItems.ingot, 1, 2));
		OreDictionary.registerOre("ingotZinc", new ItemStack(ModItems.ingot, 1, 3));
		OreDictionary.registerOre("ingotLead", new ItemStack(ModItems.ingot, 1, 4));
		OreDictionary.registerOre("ingotNickel", new ItemStack(ModItems.ingot, 1, 5));
		
		OreDictionary.registerOre("nuggetCopper", new ItemStack(ModItems.nugget, 1, 0));
		OreDictionary.registerOre("nuggetSilver", new ItemStack(ModItems.nugget, 1, 1));
		OreDictionary.registerOre("nuggetTin", new ItemStack(ModItems.nugget, 1, 2));
		OreDictionary.registerOre("nuggetZinc", new ItemStack(ModItems.nugget, 1, 3));
		OreDictionary.registerOre("nuggetLead", new ItemStack(ModItems.nugget, 1, 4));
		OreDictionary.registerOre("nuggetNickel", new ItemStack(ModItems.nugget, 1, 5));
		
		OreDictionary.registerOre("heart", ModItems.heart);
		OreDictionary.registerOre("soulStealer", ModTools.soulStealer);
		
		OreDictionary.registerOre("pickaxeCopper", ModTools.copperPickaxe);
		OreDictionary.registerOre("pickaxeSilver", ModTools.silverPickaxe);
		OreDictionary.registerOre("pickaxeTin", ModTools.tinPickaxe);
		OreDictionary.registerOre("pickaxeZinc", ModTools.zincPickaxe);
		OreDictionary.registerOre("pickaxeLead", ModTools.leadPickaxe);
		OreDictionary.registerOre("pickaxeNickel", ModTools.nickelPickaxe);
		
		OreDictionary.registerOre("axeCopper", ModTools.copperAxe);
		OreDictionary.registerOre("axeSilver", ModTools.silverAxe);
		OreDictionary.registerOre("axeTin", ModTools.tinAxe);
		OreDictionary.registerOre("axeZinc", ModTools.zincAxe);
		OreDictionary.registerOre("axeLead", ModTools.leadAxe);
		OreDictionary.registerOre("axeNickel", ModTools.nickelAxe);
		
		OreDictionary.registerOre("hoeCopper", ModTools.copperHoe);
		OreDictionary.registerOre("hoeSilver", ModTools.silverHoe);
		OreDictionary.registerOre("hoeTin", ModTools.tinHoe);
		OreDictionary.registerOre("hoeZinc", ModTools.zincHoe);
		OreDictionary.registerOre("hoeLead", ModTools.leadHoe);
		OreDictionary.registerOre("hoeNickel", ModTools.nickelHoe);
		
		OreDictionary.registerOre("shovelCopper", ModTools.copperShovel);
		OreDictionary.registerOre("shovelSilver", ModTools.silverShovel);
		OreDictionary.registerOre("shovelTin", ModTools.tinShovel);
		OreDictionary.registerOre("shovelZinc", ModTools.zincShovel);
		OreDictionary.registerOre("shovelLead", ModTools.leadShovel);
		OreDictionary.registerOre("shovelNickel", ModTools.nickelShovel);
		
		OreDictionary.registerOre("swordCopper", ModTools.copperSword);
		OreDictionary.registerOre("swordSilver", ModTools.silverSword);
		OreDictionary.registerOre("swordTin", ModTools.tinSword);
		OreDictionary.registerOre("swordZinc", ModTools.zincSword);
		OreDictionary.registerOre("swordLead", ModTools.leadSword);
		OreDictionary.registerOre("swordNickel", ModTools.nickelSword);
		
		OreDictionary.registerOre("generator", ModBlocks.generator);
		OreDictionary.registerOre("generatorFrame", ModBlocks.generatorFrame);
		
		OreDictionary.registerOre("basicChip", new ItemStack(ModItems.chip, 1, 0));
		OreDictionary.registerOre("advancedChip", new ItemStack(ModItems.chip, 1, 1));
	}

}
