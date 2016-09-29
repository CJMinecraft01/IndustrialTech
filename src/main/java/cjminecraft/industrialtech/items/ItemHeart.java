package cjminecraft.industrialtech.items;

import java.util.List;

import cjminecraft.industrialtech.IndustrialTech;
import cjminecraft.industrialtech.Reference;
import cjminecraft.industrialtech.util.IndustrialTechItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemHeart extends IndustrialTechItem {

	public ItemHeart(String unlocalizedName) {
		super(unlocalizedName);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player,
			EnumHand hand) {
		if(!(player.getHealth() == player.getMaxHealth())) {
			player.heal(2);
			ItemStack item = player.getHeldItem(hand);
			item.stackSize--;
		}
		return super.onItemRightClick(stack, world, player, hand);
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.EAT;
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		tooltip.add(TextFormatting.RED + IndustrialTech.lang.localize("heart.tooltip"));
	}
	
}
