package cjminecraft.industrialtech.items;

import java.util.List;
import java.util.Set;

import com.google.common.collect.Sets;

import cjminecraft.industrialtech.IndustrialTech;
import cjminecraft.industrialtech.Reference;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.util.EnumHelper;

public class ItemSoulStealer extends ItemTool {
	
	private static final Set<Block> EFFECTIVE_BLOCKS = Sets.newHashSet(new Block[] {});

	public ItemSoulStealer(String unlocalizedName) {
		super(EnumHelper.addToolMaterial("soul_stealer", 0, 100, 0, 1, 0), EFFECTIVE_BLOCKS);
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(new ResourceLocation(Reference.MODID, unlocalizedName));
		this.setCreativeTab(IndustrialTech.items);
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		tooltip.add(TextFormatting.AQUA + IndustrialTech.lang.localize("soul_stealer.tooltip"));
	}

}
