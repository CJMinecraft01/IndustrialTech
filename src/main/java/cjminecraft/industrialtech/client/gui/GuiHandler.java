package cjminecraft.industrialtech.client.gui;

import cjminecraft.industrialtech.container.ContainerGenerator;
import cjminecraft.industrialtech.container.ContainerMiniChest;
import cjminecraft.industrialtech.tileentity.TileEntityGenerator;
import cjminecraft.industrialtech.tileentity.TileEntityMiniChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
	
	public static final int MINI_CHEST = 0;
	public static final int GENERATOR = 1;
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == MINI_CHEST) {
			return new ContainerMiniChest(player.inventory, (TileEntityMiniChest) world.getTileEntity(new BlockPos(x, y, z)));
		}
		if(ID == GENERATOR) {
			return new ContainerGenerator(player.inventory, (TileEntityGenerator) world.getTileEntity(new BlockPos(x, y, z)));
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == MINI_CHEST) {
			return new GuiMiniChest(player.inventory, (TileEntityMiniChest) world.getTileEntity(new BlockPos(x, y, z)));
		}
		if(ID == GENERATOR) {
			return new GuiGenerator(player.inventory, (TileEntityGenerator) world.getTileEntity(new BlockPos(x, y, z)));
		}
		return null;
	}
	
	

}
