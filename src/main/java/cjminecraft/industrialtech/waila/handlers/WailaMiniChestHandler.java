package cjminecraft.industrialtech.waila.handlers;

import java.util.List;

import cjminecraft.industrialtech.IndustrialTech;
import cjminecraft.industrialtech.init.ModBlocks;
import cjminecraft.industrialtech.tileentity.TileEntityMiniChest;
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

public class WailaMiniChestHandler implements IWailaDataProvider {

	@Override
	public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
		return new ItemStack(ModBlocks.miniChest);
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
		int size = nbt.getInteger("stackSize");
		String name = nbt.getString("name");
		if(size == 0 || name == "") {
			currenttip.add(TextFormatting.RED + IndustrialTech.lang.localize("noitem"));
		} else {
			currenttip.add(IndustrialTech.lang.localize("item") + ": " + TextFormatting.YELLOW + size + TextFormatting.GRAY + " x " + TextFormatting.YELLOW + name);
		}
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
		TileEntityMiniChest tile = (TileEntityMiniChest) te;
		ItemStack stack = tile.getItemStack();
		if(!(stack.stackSize <= 0)) {
			tag.setInteger("stackSize", stack.stackSize);
		} else {
			tag.setInteger("stackSize", 0);
		}
		tag.setString("name", stack.getDisplayName());
		return tag;
	}

}
