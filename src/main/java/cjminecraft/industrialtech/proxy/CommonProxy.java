package cjminecraft.industrialtech.proxy;

import cjminecraft.industrialtech.IndustrialTech;
import cjminecraft.industrialtech.Reference;
import cjminecraft.industrialtech.client.gui.GuiHandler;
import cjminecraft.industrialtech.tileentity.TileEntityEnergyPipe;
import cjminecraft.industrialtech.tileentity.TileEntityGenerator;
import cjminecraft.industrialtech.tileentity.TileEntityMiniChest;
import cjminecraft.industrialtech.worldgen.OreWorldGen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {
	
	public void init() {
		GameRegistry.registerWorldGenerator(new OreWorldGen(), 0);
	}
	
	public void registerRender() {
		
	}
	
	public void registerModelBakeryVariants() {
		
	}
	
	public void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityMiniChest.class, Reference.MODID + ":mini_chest");
		GameRegistry.registerTileEntity(TileEntityGenerator.class, Reference.MODID + ":generator");
		GameRegistry.registerTileEntity(TileEntityEnergyPipe.class, Reference.MODID + ":energy_pipe");
	}

}
