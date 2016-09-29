package cjminecraft.industrialtech.waila.handlers;

import java.util.List;

import cjminecraft.industrialtech.IndustrialTech;
import cjminecraft.industrialtech.init.ModBlocks;
import cjminecraft.industrialtech.tileentity.TileEntityEnergyPipe;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class WailaEnergyPipeHandler implements IWailaDataProvider {

	@Override
	public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
		return new ItemStack(ModBlocks.energyPipe);
	}

	@Override
	public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor,
			IWailaConfigHandler config) {
		return currenttip;
	}

	@Override
	public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor,
			IWailaConfigHandler config) {
		NBTTagCompound nbt = accessor.getNBTData();
		int rf = nbt.getInteger("Energy");
		currenttip.add(TextFormatting.GREEN + IndustrialTech.lang.localize("rf") + ": " + rf + IndustrialTech.lang.localize("rf") + "/" + 1000 + IndustrialTech.lang.localize("rf"));
		return currenttip;
	}

	@Override
	public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor,
			IWailaConfigHandler config) {
		return currenttip;
	}

	@Override
	public NBTTagCompound getNBTData(EntityPlayerMP player, TileEntity te, NBTTagCompound tag, World world,
			BlockPos pos) {
		TileEntityEnergyPipe tile = (TileEntityEnergyPipe) te;
		tag = te.getTileData();
		return tag;
	}

}
