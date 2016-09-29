package cjminecraft.industrialtech.waila;

import cjminecraft.industrialtech.blocks.BlockCompressed;
import cjminecraft.industrialtech.blocks.BlockEnergyPipe;
import cjminecraft.industrialtech.blocks.BlockGenerator;
import cjminecraft.industrialtech.blocks.BlockMetalCompressed;
import cjminecraft.industrialtech.blocks.BlockMiniChest;
import cjminecraft.industrialtech.init.ModBlocks;
import cjminecraft.industrialtech.waila.handlers.WailaEnergyPipeHandler;
import cjminecraft.industrialtech.waila.handlers.WailaGeneratorHandler;
import cjminecraft.industrialtech.waila.handlers.WailaMetalCompressedHandler;
import cjminecraft.industrialtech.waila.handlers.WailaMiniChestHandler;
import mcp.mobius.waila.api.IWailaRegistrar;
import net.minecraft.item.ItemStack;

public class Waila {
	
	public static void load(IWailaRegistrar registrar) {
		registrar.registerBodyProvider(new WailaMiniChestHandler(), BlockMiniChest.class);
		registrar.registerNBTProvider(new WailaMiniChestHandler(), BlockMiniChest.class);
		registrar.registerBodyProvider(new WailaMetalCompressedHandler(), BlockMetalCompressed.class);
		registrar.registerBodyProvider(new WailaGeneratorHandler(), BlockGenerator.class);
		registrar.registerNBTProvider(new WailaGeneratorHandler(), BlockGenerator.class);
		registrar.registerStackProvider(new WailaGeneratorHandler(), BlockGenerator.class);
		registrar.registerNBTProvider(new WailaEnergyPipeHandler(), BlockEnergyPipe.class);
		registrar.registerBodyProvider(new WailaEnergyPipeHandler(), BlockEnergyPipe.class);
	}

}
