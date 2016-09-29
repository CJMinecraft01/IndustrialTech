package cjminecraft.industrialtech.blocks;

import java.util.List;

import cjminecraft.industrialtech.IndustrialTech;
import cjminecraft.industrialtech.Reference;
import cjminecraft.industrialtech.blocks.item.IMetaBlockName;
import cjminecraft.industrialtech.util.IndustrialTechBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.World;

public class BlockOre extends IndustrialTechBlock implements IMetaBlockName {
	
	public static final PropertyEnum TYPE = PropertyEnum.create("type", BlockOre.OreType.class);

	public BlockOre(String unlocalizedName) {
		super(Material.ROCK, unlocalizedName);
		this.setHardness(3);
		this.setResistance(15);
		this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, OreType.COPPER));
	}
	
	@Override
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) {
		for(int i = 0; i < OreType.values().length; i++) {
			list.add(new ItemStack(itemIn, 1, i));
		}
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		if(meta == 0) {
			return getDefaultState().withProperty(TYPE, OreType.COPPER);
		}
		if(meta == 1) {
			return getDefaultState().withProperty(TYPE, OreType.SILVER);
		}
		if(meta == 2) {
			return getDefaultState().withProperty(TYPE, OreType.TIN);
		}
		if(meta == 3) {
			return getDefaultState().withProperty(TYPE, OreType.ZINC);
		}
		if(meta == 4) {
			return getDefaultState().withProperty(TYPE, OreType.LEAD);
		}
		if(meta == 5) {
			return getDefaultState().withProperty(TYPE, OreType.NICKEL);
		}
		return getDefaultState().withProperty(TYPE, OreType.COPPER);
	}
	
	@Override
	public int damageDropped(IBlockState state) {
	    return getMetaFromState(state);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
	    OreType type = (OreType) state.getValue(TYPE);
	    return type.getID();
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
	    return new BlockStateContainer(this, new IProperty[] { TYPE });
	}
	
	public enum OreType implements IStringSerializable {
	    COPPER(0, "copper"),
	    SILVER(1, "silver"),
	    TIN(2, "tin"),
	    ZINC(3, "zinc"),
	    LEAD(4, "lead"),
	    NICKEL(5, "nickel");

	    private int ID;
	    private String name;
	    
	    private OreType(int ID, String name) {
	        this.ID = ID;
	        this.name = name;
	    }
	    
	    @Override
	    public String getName() {
	        return name;
	    }

	    public int getID() {
	        return ID;
	    }
	    
	    @Override
	    public String toString() {
	        return getName();
	    }
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
