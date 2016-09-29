package cjminecraft.industrialtech.blocks;

import java.util.List;

import cjminecraft.industrialtech.handlers.EnumHandler.ConnectionTypes;
import cjminecraft.industrialtech.util.IndustrialTechBlock;
import cjminecraft.industrialtech.util.Utils;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPipe extends IndustrialTechBlock {

	public static final PropertyEnum NORTH = PropertyEnum.create("north", ConnectionTypes.class);
	public static final PropertyEnum SOUTH = PropertyEnum.create("south", ConnectionTypes.class);
	public static final PropertyEnum EAST = PropertyEnum.create("east", ConnectionTypes.class);
	public static final PropertyEnum WEST = PropertyEnum.create("west", ConnectionTypes.class);
	public static final PropertyEnum UP = PropertyEnum.create("up", ConnectionTypes.class);
	public static final PropertyEnum DOWN = PropertyEnum.create("down", ConnectionTypes.class);

	public static final AxisAlignedBB CENTER_AABB = new AxisAlignedBB(0.375D, 0.375D, 0.375D, 0.625D, 0.625D, 0.625D);
	public static final AxisAlignedBB NORTH_AABB = new AxisAlignedBB(0.625D, 0.625D, 0.0D, 0.3125D, 0.3125D, 0.8125D);
	public static final AxisAlignedBB SOUTH_AABB = new AxisAlignedBB(0.375D, 0.375D, 0.0D, 0.6875D, 0.6875D, 0.1875D);
	public static final AxisAlignedBB EAST_AABB = new AxisAlignedBB(0.375D, 0.375D, 0.0D, 0.6875D, 0.6875D, 0.1875D);
	public static final AxisAlignedBB WEST_AABB = new AxisAlignedBB(0.375D, 0.375D, 0.0D, 0.6875D, 0.6875D, 0.1875D);
	public static final AxisAlignedBB UP_AABB = new AxisAlignedBB(0.375D, 0.375D, 0.0D, 0.6875D, 0.6875D, 0.1875D);
	public static final AxisAlignedBB DOWN_AABB = new AxisAlignedBB(0.375D, 0.375D, 0.0D, 0.6875D, 0.6875D, 0.1875D);
	
	public BlockPipe(String unlocalizedName) {
		super(Material.IRON, unlocalizedName);
		this.setResistance(10.0F);
		this.setHardness(3.0F);
		this.setDefaultState(this.blockState.getBaseState().withProperty(NORTH, ConnectionTypes.NONE)
				.withProperty(SOUTH, ConnectionTypes.NONE).withProperty(EAST, ConnectionTypes.NONE)
				.withProperty(WEST, ConnectionTypes.NONE).withProperty(UP, ConnectionTypes.NONE)
				.withProperty(DOWN, ConnectionTypes.NONE));
		this.useNeighborBrightness = true;
	}
	
	@Override
	public void addCollisionBoxToList(IBlockState state, World world, BlockPos pos, AxisAlignedBB entityBox,
			List<AxisAlignedBB> collidingBoxes, Entity entityIn) {
		state = state.getActualState(world, pos);
		addCollisionBoxToList(pos, entityBox, collidingBoxes, CENTER_AABB);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		AxisAlignedBB AABB = new AxisAlignedBB(CENTER_AABB.minX, CENTER_AABB.minY, CENTER_AABB.minZ, CENTER_AABB.maxX, CENTER_AABB.maxY, CENTER_AABB.maxZ);
		return AABB;
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { NORTH, SOUTH, EAST, WEST, UP, DOWN });
	}

	@Override
	public boolean isRotateableBlock() {
		return false;
	}

	@Override
	public boolean isMachineBlock() {
		return true;
	}

	@Override
	public IProperty getPropertyDirection() {
		return null;
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}
	
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
		state = Utils.calculateConnectionType(world.getTileEntity(pos.north()), state, NORTH);
		state = Utils.calculateConnectionType(world.getTileEntity(pos.south()), state, SOUTH);
		state = Utils.calculateConnectionType(world.getTileEntity(pos.east()), state, EAST);
		state = Utils.calculateConnectionType(world.getTileEntity(pos.west()), state, WEST);
		state = Utils.calculateConnectionType(world.getTileEntity(pos.up()), state, UP);
		state = Utils.calculateConnectionType(world.getTileEntity(pos.down()), state, DOWN);
		return state;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.blockState.getBaseState();
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return 0;
	}
	
	@Override
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos,
			EnumFacing side) {
		return true;
	}
	
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

}
