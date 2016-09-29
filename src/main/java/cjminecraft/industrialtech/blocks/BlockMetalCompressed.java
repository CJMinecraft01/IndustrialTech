package cjminecraft.industrialtech.blocks;

import java.util.List;

import cjminecraft.industrialtech.IndustrialTech;
import cjminecraft.industrialtech.Reference;
import cjminecraft.industrialtech.blocks.item.IMetaBlockName;
import cjminecraft.industrialtech.handlers.EnumHandler;
import cjminecraft.industrialtech.util.IndustrialTechBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateBase;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMetalCompressed extends IndustrialTechBlock implements IMetaBlockName {
	
	public static final PropertyEnum TYPE = PropertyEnum.create("type", EnumHandler.IngotTypes.class);

	public BlockMetalCompressed(String unlocalizedName) {
		super(Material.IRON, unlocalizedName);
		this.setHardness(3);
		this.setResistance(15);
		this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, EnumHandler.IngotTypes.COPPER));
	}
	
	@Override
	public boolean isBeaconBase(IBlockAccess worldObj, BlockPos pos, BlockPos beacon) {
		return true;
	}
	
	@Override
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) {
		for(int i = 0; i < EnumHandler.IngotTypes.values().length; i++) {
			list.add(new ItemStack(itemIn, 1, i));
		}
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		if(meta == 0) {
			return getDefaultState().withProperty(TYPE, EnumHandler.IngotTypes.COPPER);
		}
		if(meta == 1) {
			return getDefaultState().withProperty(TYPE, EnumHandler.IngotTypes.SILVER);
		}
		if(meta == 2) {
			return getDefaultState().withProperty(TYPE, EnumHandler.IngotTypes.TIN);
		}
		if(meta == 3) {
			return getDefaultState().withProperty(TYPE, EnumHandler.IngotTypes.ZINC);
		}
		if(meta == 4) {
			return getDefaultState().withProperty(TYPE, EnumHandler.IngotTypes.LEAD);
		}
		if(meta == 5) {
			return getDefaultState().withProperty(TYPE, EnumHandler.IngotTypes.NICKEL);
		}
		return getDefaultState().withProperty(TYPE, EnumHandler.IngotTypes.COPPER);
	}
	
	@Override
	public int damageDropped(IBlockState state) {
	    return getMetaFromState(state);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
	    EnumHandler.IngotTypes type = (EnumHandler.IngotTypes) state.getValue(TYPE);
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
			return "copper";
		}
		if(stack.getItemDamage() == 1) {
			return "silver";
		}
		if(stack.getItemDamage() == 2) {
			return "tin";
		}
		if(stack.getItemDamage() == 3) {
			return "zinc";
		}
		if(stack.getItemDamage() == 4) {
			return "lead";
		}
		if(stack.getItemDamage() == 5) {
			return "nickel";
		}
		return "copper";
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
