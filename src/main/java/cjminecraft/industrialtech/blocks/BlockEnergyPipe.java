package cjminecraft.industrialtech.blocks;

import cjminecraft.industrialtech.tileentity.TileEntityEnergyPipe;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockEnergyPipe extends BlockPipe implements ITileEntityProvider {

	public BlockEnergyPipe(String unlocalizedName) {
		super(unlocalizedName);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityEnergyPipe();
	}

}
