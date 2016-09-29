package cjminecraft.industrialtech.init;

import cjminecraft.industrialtech.Reference;
import cjminecraft.industrialtech.blocks.BlockCompressed;
import cjminecraft.industrialtech.blocks.BlockEnergyPipe;
import cjminecraft.industrialtech.blocks.BlockGenerator;
import cjminecraft.industrialtech.blocks.BlockGeneratorFrame;
import cjminecraft.industrialtech.blocks.BlockMetalCompressed;
import cjminecraft.industrialtech.blocks.BlockMiniChest;
import cjminecraft.industrialtech.blocks.BlockOre;
import cjminecraft.industrialtech.blocks.item.ItemBlockGenerator;
import cjminecraft.industrialtech.blocks.item.ItemBlockMeta;
import cjminecraft.industrialtech.blocks.item.ItemBlockMiniChest;
import cjminecraft.industrialtech.handlers.EnumHandler;
import cjminecraft.industrialtech.util.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks {

	//Basics Update 0.1.0
	public static Block ore;
	
	//Compressed Update 0.2.0
	public static Block metalCompressed;
	public static Block cobbleCompressed;
	public static Block graniteCompressed;
	public static Block dioriteCompressed;
	public static Block andesiteCompressed;
	public static Block dirtCompressed;
	public static Block sandCompressed;
	public static Block gravelCompressed;
	public static Block netherrackCompressed;
	public static Block soulsandCompressed;
	public static Block miniChest;
	
	//Redstone Flux Update 0.3.0
	public static Block generator;
	public static Block generatorFrame;
	public static Block energyPipe;
	
	public static void init() {
		ore = new BlockOre("ore");
		metalCompressed = new BlockMetalCompressed("metal_compressed");
		cobbleCompressed = new BlockCompressed("cobblestone", false);
		graniteCompressed = new BlockCompressed("granite", false);
		dioriteCompressed = new BlockCompressed("diorite", false);
		andesiteCompressed = new BlockCompressed("andesite", false);
		dirtCompressed = new BlockCompressed("dirt", true);
		sandCompressed = new BlockCompressed("sand", true);
		gravelCompressed = new BlockCompressed("gravel", true);
		netherrackCompressed = new BlockCompressed("netherrack", false);
		soulsandCompressed = new BlockCompressed("soulsand", true);
		miniChest = new BlockMiniChest("mini_chest");
		
		generator = new BlockGenerator("generator");
		generatorFrame = new BlockGeneratorFrame("generator_frame");
		energyPipe = new BlockEnergyPipe("energy_pipe");
	}
	
	public static void register() {
		registerBlock(ore, new ItemBlockMeta(ore));
		registerBlock(metalCompressed, new ItemBlockMeta(metalCompressed));
		
		registerBlock(cobbleCompressed, new ItemBlockMeta(cobbleCompressed));
		registerBlock(graniteCompressed, new ItemBlockMeta(graniteCompressed));
		registerBlock(dioriteCompressed, new ItemBlockMeta(dioriteCompressed));
		registerBlock(andesiteCompressed, new ItemBlockMeta(andesiteCompressed));
		registerBlock(dirtCompressed, new ItemBlockMeta(dirtCompressed));
		registerBlock(sandCompressed, new ItemBlockMeta(sandCompressed));
		registerBlock(gravelCompressed, new ItemBlockMeta(gravelCompressed));
		registerBlock(netherrackCompressed, new ItemBlockMeta(netherrackCompressed));
		registerBlock(soulsandCompressed, new ItemBlockMeta(soulsandCompressed));
		registerBlock(miniChest, new ItemBlockMiniChest(miniChest));
		
		registerBlock(generator, new ItemBlockGenerator(generator));
		registerBlock(generatorFrame);
		registerBlock(energyPipe);
	}
	
	public static void registerRenders() {
		for(int i = 0; i < BlockOre.OreType.values().length; i++) {
			registerRender(ore, i, BlockOre.OreType.values()[i].getName() + "_ore");
		}
		for(int i = 0; i < EnumHandler.IngotTypes.values().length; i++) {
			registerRender(metalCompressed, i, EnumHandler.IngotTypes.values()[i].getName() + "_block");
		}
		for(int i = 0; i < EnumHandler.CompressedTypes.values().length; i++) {
			registerRender(cobbleCompressed, i, EnumHandler.CompressedTypes.values()[i].getName() + "_cobblestone");
			registerRender(graniteCompressed, i, EnumHandler.CompressedTypes.values()[i].getName() + "_granite");
			registerRender(dioriteCompressed, i, EnumHandler.CompressedTypes.values()[i].getName() + "_diorite");
			registerRender(andesiteCompressed, i, EnumHandler.CompressedTypes.values()[i].getName() + "_andesite");
			registerRender(dirtCompressed, i, EnumHandler.CompressedTypes.values()[i].getName() + "_dirt");
			registerRender(sandCompressed, i, EnumHandler.CompressedTypes.values()[i].getName() + "_sand");
			registerRender(gravelCompressed, i, EnumHandler.CompressedTypes.values()[i].getName() + "_gravel");
			registerRender(netherrackCompressed, i, EnumHandler.CompressedTypes.values()[i].getName() + "_netherrack");
			registerRender(soulsandCompressed, i, EnumHandler.CompressedTypes.values()[i].getName() + "_soulsand");
		}
		registerRender(miniChest);
		registerRender(generatorFrame);
		for(int i = 0; i < EnumHandler.GeneratorTypes.values().length; i++) {
			registerRender(generator, i, "generator_" + EnumHandler.GeneratorTypes.values()[i].getName());
		}
		registerRender(energyPipe);
	}
	
	public static void registerBlock(Block block) {
		GameRegistry.register(block);
		GameRegistry.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
		
		Utils.getLogger().info("Registered Block: " + block.getUnlocalizedName().substring(5));
	}
	
	public static void registerBlock(Block block, ItemBlock itemBlock) {
		GameRegistry.register(block);
		GameRegistry.register(itemBlock.setRegistryName(block.getRegistryName()));
		Utils.getLogger().info("Registered Block: " + block.getUnlocalizedName().substring(5));
	}
	
	public static void registerRender(Block block) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(new ResourceLocation(Reference.MODID, block.getUnlocalizedName().substring(5)), "inventory"));
		Utils.getLogger().info("Registered Render For Block: " + block.getUnlocalizedName().substring(5));
	}
	
	public static void registerRender(Block block, int meta) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), meta, new ModelResourceLocation(new ResourceLocation(Reference.MODID, block.getUnlocalizedName().substring(5)), "inventory"));
		Utils.getLogger().info("Registered Render For Block: " + block.getUnlocalizedName().substring(5));
	}
	
	public static void registerRender(Block block, String fileName) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(new ResourceLocation(Reference.MODID, fileName), "inventory"));
		Utils.getLogger().info("Registered Render For Block: " + block.getUnlocalizedName().substring(5));
	}
	
	public static void registerRender(Block block, int meta, String fileName) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), meta, new ModelResourceLocation(new ResourceLocation(Reference.MODID, fileName), "inventory"));
		Utils.getLogger().info("Registered Render For Block: " + block.getUnlocalizedName().substring(5));
	}
	
}
