package cjminecraft.industrialtech.blocks;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import cjminecraft.industrialtech.IndustrialTech;
import cjminecraft.industrialtech.Reference;
import cjminecraft.industrialtech.blocks.item.IMetaBlockName;
import cjminecraft.industrialtech.handlers.EnumHandler;
import cjminecraft.industrialtech.util.IndustrialTechBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCompressed extends IndustrialTechBlock implements IMetaBlockName {
	
	public static boolean fallInstantly;
	
	protected static final AxisAlignedBB SOUL_SAND_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.875D, 1.0D);
	
	public static final PropertyEnum TYPE = PropertyEnum.create("type", EnumHandler.CompressedTypes.class);

	public BlockCompressed(String unlocalizedName, boolean isGroundMat) {
		super(isGroundMat ? Material.GROUND : Material.ROCK, unlocalizedName);
		this.setHardness(3);
		this.setResistance(15);
		this.setHarvestLevel("pickaxe", 0);
		if(unlocalizedName == "soulsand") {
			this.setSoundType(SoundType.SAND);
		}
		if(unlocalizedName == "dirt") {
			this.setHardness(0.5f);
			this.setHarvestLevel("shovel", 0);
			this.setResistance(2.5f);
			this.setSoundType(SoundType.GROUND);
		}
		if(unlocalizedName == "sand" || unlocalizedName == "gravel") {
			this.setHardness(0.5f);
			this.setResistance(2.5f);
			this.setHarvestLevel("shovel", 0);
			this.setSoundType(SoundType.SAND);
		}
		this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, EnumHandler.CompressedTypes.SINGLE_COMPRESSED));
		
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		if(source.getBlockState(pos).getBlock().getUnlocalizedName().contains("soulsand")) {
	        return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.875D, 1.0D);
		}
		return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
	}
	
	public AxisAlignedBB getCollisionBoundingBox(World world, BlockPos pos, IBlockState state) {
		if(world.getBlockState(pos).getBlock().getUnlocalizedName().contains("soulsand")) {
	        return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.875D, 1.0D);
		}
		return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
	}
	
	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
		if(world.getBlockState(pos).getBlock().getUnlocalizedName().contains("soulsand")) {
			entity.motionX *= 0.4D;
			entity.motionZ *= 0.4D;
		}
	}
	
	@Override
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) {
		for(int i = 0; i < EnumHandler.CompressedTypes.values().length; i++) {
			list.add(new ItemStack(itemIn, 1, i));
		}
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		if(meta == 0) {
			return getDefaultState().withProperty(TYPE, EnumHandler.CompressedTypes.SINGLE_COMPRESSED);
		}
		if(meta == 1) {
			return getDefaultState().withProperty(TYPE, EnumHandler.CompressedTypes.DOUBLE_COMPRESSED);
		}
		if(meta == 2) {
			return getDefaultState().withProperty(TYPE, EnumHandler.CompressedTypes.TRIPLE_COMPRESSED);
		}
		if(meta == 3) {
			return getDefaultState().withProperty(TYPE, EnumHandler.CompressedTypes.QUADRUPLE_COMPRESSED);
		}
		if(meta == 4) {
			return getDefaultState().withProperty(TYPE, EnumHandler.CompressedTypes.PENTUPLE_COMPRESSED);
		}
		if(meta == 5) {
			return getDefaultState().withProperty(TYPE, EnumHandler.CompressedTypes.SEXTUPLE_COMPRESSED);
		}
		if(meta == 6) {
			return getDefaultState().withProperty(TYPE, EnumHandler.CompressedTypes.SEPTUPLE_COMPRESSED);
		}
		if(meta == 7) {
			return getDefaultState().withProperty(TYPE, EnumHandler.CompressedTypes.OCTUPLE_COMPRESSED);
		}
		return getDefaultState().withProperty(TYPE, EnumHandler.CompressedTypes.SINGLE_COMPRESSED);
	}
	
	@Override
	public int damageDropped(IBlockState state) {
	    return getMetaFromState(state);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
	    EnumHandler.CompressedTypes type = (EnumHandler.CompressedTypes) state.getValue(TYPE);
	    return type.getID();
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
	    return new BlockStateContainer(this, new IProperty[] { TYPE });
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos,
			EntityPlayer player) {
		return new ItemStack(Item.getItemFromBlock(this), 1, this.getMetaFromState(world.getBlockState(pos)));
	}

	@Override
	public String getSpecialName(ItemStack stack) {
		if(stack.getItemDamage() == 0) {
			return "single_compressed";
		}
		if(stack.getItemDamage() == 1) {
			return "double_compressed";
		}
		if(stack.getItemDamage() == 2) {
			return "triple_compressed";
		}
		if(stack.getItemDamage() == 3) {
			return "quadruple_compressed";
		}
		if(stack.getItemDamage() == 4) {
			return "pentuple_compressed";
		}
		if(stack.getItemDamage() == 5) {
			return "sextuple_compressed";
		}
		if(stack.getItemDamage() == 6) {
			return "septuple_compressed";
		}
		if(stack.getItemDamage() == 7) {
			return "octuple_compressed";
		}
		return "single_compressed";
	}
	
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if(!worldIn.isRemote) {
			if(state.getBlock().getUnlocalizedName().contains("gravel") || state.getBlock().getUnlocalizedName().contains("sand")) {
				if(!state.getBlock().getUnlocalizedName().contains("soulsand")) {
					this.checkFallable(worldIn, pos);
				}
			}
		}
	}
	
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn)
    {
        worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
    }
    
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
    }
    
    protected void onStartFalling(EntityFallingBlock fallingEntity)
    {
    }
    
    public int tickRate(World worldIn)
    {
        return 2;
    }
    
    public void onEndFalling(World worldIn, BlockPos pos)
    {
    }
    
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        if (rand.nextInt(16) == 0)
        {
            BlockPos blockpos = pos.down();

            if (canFallThrough(worldIn.getBlockState(blockpos)))
            {
                double d0 = (double)((float)pos.getX() + rand.nextFloat());
                double d1 = (double)pos.getY() - 0.05D;
                double d2 = (double)((float)pos.getZ() + rand.nextFloat());
                worldIn.spawnParticle(EnumParticleTypes.FALLING_DUST, d0, d1, d2, 0.0D, 0.0D, 0.0D, new int[] {Block.getStateId(stateIn)});
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public int func_189876_x(IBlockState p_189876_1_)
    {
        return -16777216;
    }
	
    private void checkFallable(World worldIn, BlockPos pos)
    {
        if ((worldIn.isAirBlock(pos.down()) || canFallThrough(worldIn.getBlockState(pos.down()))) && pos.getY() >= 0)
        {
            int i = 32;

            if (!fallInstantly && worldIn.isAreaLoaded(pos.add(-32, -32, -32), pos.add(32, 32, 32)))
            {
                if (!worldIn.isRemote)
                {
                    EntityFallingBlock entityfallingblock = new EntityFallingBlock(worldIn, (double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, worldIn.getBlockState(pos));
                    this.onStartFalling(entityfallingblock);
                    worldIn.spawnEntityInWorld(entityfallingblock);
                }
            }
            else
            {
                worldIn.setBlockToAir(pos);
                BlockPos blockpos;

                for (blockpos = pos.down(); (worldIn.isAirBlock(blockpos) || canFallThrough(worldIn.getBlockState(blockpos))) && blockpos.getY() > 0; blockpos = blockpos.down())
                {
                    ;
                }

                if (blockpos.getY() > 0)
                {
                    worldIn.setBlockState(blockpos.up(), this.getDefaultState());
                }
            }
        }
    }
	
    public static boolean canFallThrough(IBlockState state)
    {
        Block block = state.getBlock();
        Material material = state.getMaterial();
        return block == Blocks.FIRE || material == Material.AIR || material == Material.WATER || material == Material.LAVA;
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
