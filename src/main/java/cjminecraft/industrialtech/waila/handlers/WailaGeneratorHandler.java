package cjminecraft.industrialtech.waila.handlers;

import java.util.List;

import cjminecraft.industrialtech.IndustrialTech;
import cjminecraft.industrialtech.blocks.BlockGenerator;
import cjminecraft.industrialtech.handlers.EnumHandler;
import cjminecraft.industrialtech.init.ModBlocks;
import cjminecraft.industrialtech.tileentity.TileEntityGenerator;
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

public class WailaGeneratorHandler implements IWailaDataProvider {

	@Override
	public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
		EnumHandler.GeneratorTypes type = (EnumHandler.GeneratorTypes) accessor.getBlockState().getValue(BlockGenerator.TYPE);
		return new ItemStack(ModBlocks.generator, 1, type.getID());
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
		int cooldown = nbt.getInteger("cooldown");
		int increasePerTick = nbt.getInteger("increasePerTick");
		int rf = nbt.getInteger("rf");
		int maxRF = nbt.getInteger("maxRF");
		if(rf >= maxRF || cooldown <= 0) {
			currenttip.add(TextFormatting.YELLOW + IndustrialTech.lang.localize("rftick") + ": 0");
		}
		else {
			currenttip.add(TextFormatting.YELLOW + IndustrialTech.lang.localize("rftick") + ": " + increasePerTick);
		}
		currenttip.add(TextFormatting.YELLOW + IndustrialTech.lang.localize("cooldown") + ": " + cooldown);
		currenttip.add(TextFormatting.YELLOW + IndustrialTech.lang.localize("timeleft") + ": " + (cooldown/20) + "s");
		currenttip.add(TextFormatting.GREEN + IndustrialTech.lang.localize("rf") + ": " + rf + IndustrialTech.lang.localize("rf") + "/" + maxRF + IndustrialTech.lang.localize("rf"));
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
		TileEntityGenerator tile = (TileEntityGenerator) te;
		tag.setInteger("cooldown", tile.getField(0));
		tag.setInteger("increasePerTick", tile.getField(1));
		tag.setInteger("rf", tile.getStorage().getEnergyStored());
		tag.setInteger("maxRF", tile.getStorage().getMaxEnergyStored());
		return tag;
	}

}
