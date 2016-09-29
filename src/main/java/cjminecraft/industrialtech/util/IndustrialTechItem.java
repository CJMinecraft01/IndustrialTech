package cjminecraft.industrialtech.util;

import cjminecraft.industrialtech.IndustrialTech;
import cjminecraft.industrialtech.Reference;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public abstract class IndustrialTechItem extends Item {
	
	public IndustrialTechItem(String unlocalizedName) {
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(new ResourceLocation(Reference.MODID, unlocalizedName));
		this.setCreativeTab(IndustrialTech.items);
	}

}
