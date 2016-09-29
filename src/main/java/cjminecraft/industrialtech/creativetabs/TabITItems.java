package cjminecraft.industrialtech.creativetabs;

import cjminecraft.industrialtech.Reference;
import cjminecraft.industrialtech.init.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class TabITItems extends CreativeTabs {

	public TabITItems() {
		super("ititems");
		//this.setBackgroundImageName("industrial_tech");
	}

	@Override
	public Item getTabIconItem() {
		return ModItems.ingot;
	}

}
