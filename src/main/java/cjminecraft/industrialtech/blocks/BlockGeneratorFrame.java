package cjminecraft.industrialtech.blocks;

import cjminecraft.industrialtech.IndustrialTech;
import cjminecraft.industrialtech.Reference;
import cjminecraft.industrialtech.util.IndustrialTechBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.util.ResourceLocation;

public class BlockGeneratorFrame extends IndustrialTechBlock {

	public BlockGeneratorFrame(String unlocalizedName) {
		super(Material.IRON, unlocalizedName);
		this.setResistance(30);
		this.setHardness(5);
	}

	@Override
	public boolean isRotateableBlock() {
		return false;
	}

	@Override
	public boolean isMachineBlock() {
		return false;
	}

	@Override
	public IProperty getPropertyDirection() {
		return null;
	}

}
