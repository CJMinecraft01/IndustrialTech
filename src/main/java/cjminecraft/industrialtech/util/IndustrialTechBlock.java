package cjminecraft.industrialtech.util;

import cjminecraft.industrialtech.IndustrialTech;
import cjminecraft.industrialtech.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.util.ResourceLocation;

public abstract class IndustrialTechBlock extends Block {
	
	public IndustrialTechBlock(Material material, String unlocalizedName) {
		super(material);
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(new ResourceLocation(Reference.MODID, unlocalizedName));
		this.setCreativeTab(IndustrialTech.blocks);
	}
	
	public abstract boolean isRotateableBlock();
	
	public abstract boolean isMachineBlock();
	
	public abstract IProperty getPropertyDirection();

}
