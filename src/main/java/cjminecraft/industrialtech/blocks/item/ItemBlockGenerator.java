package cjminecraft.industrialtech.blocks.item;

import java.util.List;

import cjminecraft.industrialtech.IndustrialTech;
import cjminecraft.industrialtech.tileentity.TileEntityGenerator;
import cjminecraft.industrialtech.util.Utils;
import cofh.api.energy.IEnergyContainerItem;
import cofh.api.energy.ItemEnergyContainer;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemBlockGenerator extends ItemBlock {
	
	public ItemBlockGenerator(Block block) {
		super(block);
		if(!(block instanceof IMetaBlockName)) {
			throw new IllegalArgumentException(String
					.format("The given Block %s is not an instance of ISpecialBlockName!", block.getUnlocalizedName()));
		}
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}
	
	public int getMetadata(int damage) {
		return damage;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName(stack) + "." + ((IMetaBlockName) this.block).getSpecialName(stack);
	}
	
	@Override
	public void onCreated(ItemStack stack, World world, EntityPlayer player) {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setInteger("maxRF", 0);
		nbt.setInteger("ipt", 0);
		nbt.setInteger("Energy", 0);
		nbt.setInteger("cooldown", 0);
		stack.setTagCompound(nbt);
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
		int increasePerTick = 0;
		int maxRF = 0;
		int rf = 0;
		int cooldown = 0;
		NBTTagCompound nbt;
		if(stack.hasTagCompound()) {
			nbt = stack.getTagCompound();
		} else {
			nbt = new NBTTagCompound();
		}
		if(!nbt.hasNoTags()) {
			if(nbt.hasKey("maxRF")) {
				maxRF = nbt.getInteger("maxRF");
			}
			if(nbt.hasKey("ipt")) {
				increasePerTick = nbt.getInteger("ipt");
			}
			if(nbt.hasKey("Energy")) {
				rf = nbt.getInteger("Energy");
			}
			if(nbt.hasKey("cooldown")) {
				cooldown = nbt.getInteger("cooldown");
			}
		}
		tooltip.add(TextFormatting.YELLOW + IndustrialTech.lang.localize("rftick") + ": " + increasePerTick);
		tooltip.add(TextFormatting.YELLOW + IndustrialTech.lang.localize("cooldown") + ": " + cooldown);
		tooltip.add(TextFormatting.GREEN + IndustrialTech.lang.localize("rf") + ": " + rf + IndustrialTech.lang.localize("rf") + "/" + maxRF + IndustrialTech.lang.localize("rf"));
	}

}
