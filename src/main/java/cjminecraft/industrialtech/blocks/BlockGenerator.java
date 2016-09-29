package cjminecraft.industrialtech.blocks;

import java.util.ArrayList;
import java.util.List;

import cjminecraft.industrialtech.IndustrialTech;
import cjminecraft.industrialtech.Reference;
import cjminecraft.industrialtech.blocks.item.IMetaBlockName;
import cjminecraft.industrialtech.client.gui.GuiHandler;
import cjminecraft.industrialtech.handlers.EnumHandler;
import cjminecraft.industrialtech.init.ModBlocks;
import cjminecraft.industrialtech.tileentity.TileEntityEnergyPipe;
import cjminecraft.industrialtech.tileentity.TileEntityGenerator;
import cjminecraft.industrialtech.util.IndustrialTechBlock;
import cofh.api.energy.EnergyStorage;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockGenerator extends IndustrialTechBlock implements IMetaBlockName, ITileEntityProvider {
	
	public static final PropertyDirection FACING = PropertyDirection.create("facing");
	public static final PropertyEnum TYPE = PropertyEnum.create("type", EnumHandler.GeneratorTypes.class);
	
	public BlockGenerator(String unlocalizedName) {
		super(Material.IRON, unlocalizedName);
		this.setResistance(30);
		this.setHardness(5);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(TYPE, EnumHandler.GeneratorTypes.FURNACE));
	}
	
	@Override
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
		for(int i = 0; i < EnumHandler.GeneratorTypes.values().length; i++) {
			list.add(new ItemStack(itemIn, 1, i));
		}
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {FACING,TYPE});
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityGenerator();
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		if(meta == 0) {
			return getDefaultState().withProperty(TYPE, EnumHandler.GeneratorTypes.FURNACE);
		}
		if(meta == 1) {
			return getDefaultState().withProperty(TYPE, EnumHandler.GeneratorTypes.FOOD);
		}
		if(meta == 2) {
			return getDefaultState().withProperty(TYPE, EnumHandler.GeneratorTypes.REDSTONE);
		}
		return getDefaultState().withProperty(TYPE, EnumHandler.GeneratorTypes.FURNACE);
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		EnumHandler.GeneratorTypes type = (EnumHandler.GeneratorTypes) state.getValue(TYPE);
		return type.getID();
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos,
			EntityPlayer player) {
		ItemStack stack = new ItemStack(Blocks.AIR);
		return stack;
	}
	
	@Override
	public int damageDropped(IBlockState state) {
		return getMetaFromState(state);
	}

	@Override
	public String getSpecialName(ItemStack stack) {
		if(stack.getItemDamage() == 0) {
			return "furnace";
		}
		if(stack.getItemDamage() == 1) {
			return "food";
		}
		if(stack.getItemDamage() == 2) {
			return "redstone";
		}
		return "furnace";
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player,
			EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if(!world.isRemote) 
			player.openGui(IndustrialTech.instance, GuiHandler.GENERATOR, world, pos.getX(), pos.getY(), pos.getZ());
		return true;
	}
	
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		TileEntityGenerator te = (TileEntityGenerator) world.getTileEntity(pos);
	    InventoryHelper.dropInventoryItems(world, pos, te);
	    if(!world.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 8, false).isCreative() && world.getGameRules().getBoolean("doTileDrops")) {
	    	ItemStack stack = new ItemStack(Item.getItemFromBlock(this), 1, getMetaFromState(state));
			stack.setTagCompound(te.getTileData());
			EntityItem item = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), stack);
			world.spawnEntityInWorld(item);
	    }
	    super.breakBlock(world, pos, state);
	}
	
	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ,
			int meta, EntityLivingBase placer) {
		return super.onBlockPlaced(worldIn, pos, BlockPistonBase.getFacingFromEntity(pos, placer), hitX, hitY, hitZ, meta, placer);
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		world.setBlockState(pos, state.withProperty(FACING, BlockPistonBase.getFacingFromEntity(pos, placer)), 2);
		TileEntityGenerator te = (TileEntityGenerator) world.getTileEntity(pos);
		if(stack.hasTagCompound()) {
			if(!stack.getTagCompound().hasNoTags()) {
				te.setField(0, stack.getTagCompound().getInteger("cooldown"));
				te.setField(1, stack.getTagCompound().getInteger("ipt"));
				te.setField(2, stack.getTagCompound().getInteger("currentRF"));
				te.setField(3, stack.getTagCompound().getInteger("maxRF"));
				EnergyStorage storage = new EnergyStorage(stack.getTagCompound().getInteger("maxRF"));
				storage.setEnergyStored(stack.getTagCompound().getInteger("currentRF"));
				te.setStorage(storage);
			}
		}
	    if (stack.hasDisplayName()) {
	        te.setCustomName(stack.getDisplayName());
	    }
	}
	
	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		return new ArrayList<ItemStack>();
	}
	
	@Override
    public boolean hasComparatorInputOverride(IBlockState state) {
    	return true;
    }
    
    @Override
    public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos) {
        return Container.calcRedstone(worldIn.getTileEntity(pos));
    }
    
    @Override
    public boolean canConnectRedstone(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
    	return true;
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
	
}
