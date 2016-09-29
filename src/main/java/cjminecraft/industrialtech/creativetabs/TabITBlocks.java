package cjminecraft.industrialtech.creativetabs;

import cjminecraft.industrialtech.init.ModBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class TabITBlocks extends CreativeTabs {

	public TabITBlocks() {
		super("itblocks");
		//this.setBackgroundImageName("industrial_tech");
	}

	@Override
	public Item getTabIconItem() {
		return Item.getItemFromBlock(ModBlocks.ore);
	}

}
