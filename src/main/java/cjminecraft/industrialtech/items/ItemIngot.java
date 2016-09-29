package cjminecraft.industrialtech.items;

import java.util.List;

import cjminecraft.industrialtech.IndustrialTech;
import cjminecraft.industrialtech.Reference;
import cjminecraft.industrialtech.handlers.EnumHandler;
import cjminecraft.industrialtech.util.IndustrialTechItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

public class ItemIngot extends IndustrialTechItem {
	
	public ItemIngot(String unlocalizedName) {
		super(unlocalizedName);
		this.setHasSubtypes(true);
	}
	
	@Override
	public boolean isBeaconPayment(ItemStack stack) {
		if(stack.getUnlocalizedName().contains("ingot")) {
			return true;
		}
		return false;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		for(int i = 0; i < EnumHandler.IngotTypes.values().length; i++) {
			if(stack.getItemDamage() == i) {
				return super.getUnlocalizedName() + "." + EnumHandler.IngotTypes.values()[i].getName();
			}
			else {
				continue;
			}
		}
	    return super.getUnlocalizedName() + ".copper";
	}

	@Override
	public void getSubItems(Item itemIn, CreativeTabs tab, List subItems) {
	    for(int i = 0; i < EnumHandler.IngotTypes.values().length; i++) {
	    	subItems.add(new ItemStack(itemIn, 1, i));
	    }
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		if(stack.getUnlocalizedName().contains("ingot")) {
			tooltip.add(TextFormatting.GRAY + IndustrialTech.lang.localize("ingot.beacon"));
		}
	}

}
