package cjminecraft.industrialtech.blocks;

import cjminecraft.industrialtech.util.IndustrialTechBlock;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockEnergyCube extends IndustrialTechBlock implements ITileEntityProvider {

	public static final PropertyDirection FACING = PropertyDirection.create("facing");
	
	public BlockEnergyCube(String unlocalizedName) {
		super(Material.IRON, unlocalizedName);
		this.setResistance(30);
		this.setHardness(5);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {FACING});
	}
	
	@Override
	public boolean isRotateableBlock() {
		return true;
	}

	@Override
	public boolean isMachineBlock() {
		return true;
	}

	@Override
	public IProperty getPropertyDirection() {
		return FACING;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return null;
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

}
