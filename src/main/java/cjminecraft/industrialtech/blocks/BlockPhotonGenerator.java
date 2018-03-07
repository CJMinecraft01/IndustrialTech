package cjminecraft.industrialtech.blocks;

import cjminecraft.industrialtech.IndustrialTech;
import cjminecraft.industrialtech.client.gui.GuiHandler;
import cjminecraft.industrialtech.init.ITBlocks;
import cjminecraft.industrialtech.tiles.TileEntityPhotonGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockPhotonGenerator extends BlockContainer {

    public static final PropertyDirection FACING = PropertyDirection.create("facing");
    public static final AxisAlignedBB[] BASE_AABB = new AxisAlignedBB[] { new AxisAlignedBB(0, 0, 0, 1, 0.25D, 1),
            new AxisAlignedBB(1, 1, 1, 0, 0.75D, 0), new AxisAlignedBB(0, 0, 0, 1, 1, 0.25D),
            new AxisAlignedBB(1, 1, 1, 0, 0, 0.75D), new AxisAlignedBB(0, 0, 0, 0.25D, 1, 1),
            new AxisAlignedBB(1, 1, 1, 0.75D, 0, 0) };

    public BlockPhotonGenerator() {
        super(Material.IRON);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
        this.useNeighborBrightness = true;
        this.setHardness(3);
        this.setResistance(20.0F);
    }

    @Override
    public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World worldIn, BlockPos pos) {
        return BASE_AABB[getMetaFromState(state)].offset(pos);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return BASE_AABB[getMetaFromState(state)];
    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
        return BASE_AABB[getMetaFromState(state)];
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullBlock(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean shouldSideBeRendered(IBlockState currentState, IBlockAccess blockAccess, BlockPos pos,
                                        EnumFacing side) {
        IBlockState actualState = blockAccess.getBlockState(pos.offset(side));
        Block block = actualState.getBlock();

        if (this == ITBlocks.PHOTON_GENERATOR) {
            if (currentState != actualState) {
                return true;
            }

            if (block == this) {
                return false;
            }
        }

        return block == this ? false : super.shouldSideBeRendered(currentState, blockAccess, pos, side);
    }

    @Override
    public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
        return true;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).getIndex();
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(FACING, EnumFacing.VALUES[meta % 6]);
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        return getDefaultState().withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, placer).getOpposite());
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityPhotonGenerator();
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return createNewTileEntity(world, getMetaFromState(state));
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        player.openGui(IndustrialTech.instance, GuiHandler.PHOTON_GENERATOR, world, pos.getX(), pos.getY(), pos.getZ());
        return super.onBlockActivated(world, pos, state, player, hand, facing, hitX, hitY, hitZ);
    }
}
