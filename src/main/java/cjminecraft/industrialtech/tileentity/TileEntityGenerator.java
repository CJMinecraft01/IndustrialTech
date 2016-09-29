package cjminecraft.industrialtech.tileentity;

import java.util.ArrayList;
import java.util.List;

import cjminecraft.industrialtech.blocks.BlockGenerator;
import cjminecraft.industrialtech.handlers.EnumHandler;
import cjminecraft.industrialtech.util.ModChecker;
import cjminecraft.industrialtech.util.TileEntityEnergyHandler;
import cjminecraft.industrialtech.util.Utils.CompatableMods;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;
import cofh.api.energy.IEnergyStorage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

public class TileEntityGenerator extends TileEntityEnergyHandler implements IInventory, IEnergyProvider, ITickable {

	private static int maxRF = 100000;

	private int increasePerTick;
	private int cooldown;

	private ItemStack[] inventory;
	private String customName;

	public TileEntityGenerator() {
		super(maxRF, 0, 1000);
		this.inventory = new ItemStack[this.getSizeInventory()];
	}

	public String getCustomName() {
		return customName;
	}

	public void setCustomName(String customName) {
		this.customName = customName;
	}

	@Override
	public String getName() {
		EnumHandler.GeneratorTypes type = EnumHandler.GeneratorTypes.values()[this.getBlockMetadata()];
		if (type == EnumHandler.GeneratorTypes.FURNACE) {
			return this.hasCustomName() ? this.customName : "container.generator.furnace";
		}
		if (type == EnumHandler.GeneratorTypes.FOOD) {
			return this.hasCustomName() ? this.customName : "container.generator.food";
		}
		if (type == EnumHandler.GeneratorTypes.REDSTONE) {
			return this.hasCustomName() ? this.customName : "container.generator.redstone";
		}
		return this.hasCustomName() ? this.customName : "container.generator.furnace";
	}

	@Override
	public boolean hasCustomName() {
		return this.customName != null && !this.customName.equals("");
	}

	@Override
	public ITextComponent getDisplayName() {
		return this.hasCustomName() ? new TextComponentString(this.getName())
				: new TextComponentTranslation(this.getName());
	}

	@Override
	public int getSizeInventory() {
		return 1;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		if (index < 0 || index >= this.getSizeInventory())
			return null;
		return this.inventory[index];
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		if (this.getStackInSlot(index) != null) {
			ItemStack itemstack;

			if (this.getStackInSlot(index).stackSize <= count) {
				itemstack = this.getStackInSlot(index);
				this.setInventorySlotContents(index, null);
				this.markDirty();
				return itemstack;
			} else {
				itemstack = this.getStackInSlot(index).splitStack(count);

				if (this.getStackInSlot(index).stackSize <= 0) {
					this.setInventorySlotContents(index, null);
				} else {
					// Just to show that changes happened
					this.setInventorySlotContents(index, this.getStackInSlot(index));
				}

				this.markDirty();
				return itemstack;
			}
		} else {
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		if (index < 0 || index >= this.getSizeInventory())
			return;

		if (stack != null && stack.stackSize > this.getInventoryStackLimit())
			stack.stackSize = this.getInventoryStackLimit();

		if (stack != null && stack.stackSize == 0)
			stack = null;

		this.inventory[index] = stack;
		this.markDirty();
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return this.worldObj.getTileEntity(this.getPos()) == this
				&& player.getDistanceSq(this.pos.add(0.5, 0.5, 0.5)) <= 64;
	}

	@Override
	public void openInventory(EntityPlayer player) {
		markDirty();
	}

	@Override
	public void closeInventory(EntityPlayer player) {
		markDirty();
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return true;
	}

	public EnergyStorage getStorage() {
		return storage;
	}

	public void setStorage(EnergyStorage storage) {
		this.storage = storage;
	}

	@Override
	public int getField(int id) {
		switch (id) {
		case 0:
			return this.cooldown;
		case 1:
			return this.increasePerTick;
		case 2:
			return this.storage.getEnergyStored();
		case 3:
			return this.maxRF;
		}
		return 0;
	}

	@Override
	public void setField(int id, int value) {
		switch (id) {
		case 0:
			this.cooldown = value;
		case 1:
			this.increasePerTick = value;
		case 2:
			this.storage.setEnergyStored(value);
		case 3:
			this.storage.setCapacity(value);
		}
	}

	@Override
	public int getFieldCount() {
		return 4;
	}

	@Override
	public void clear() {
		for (int i = 0; i < this.getSizeInventory(); i++)
			this.setInventorySlotContents(i, null);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		NBTTagList list = new NBTTagList();
		for (int i = 0; i < this.getSizeInventory(); ++i) {
			if (this.getStackInSlot(i) != null) {
				NBTTagCompound stackTag = new NBTTagCompound();
				stackTag.setByte("Slot", (byte) i);
				this.getStackInSlot(i).writeToNBT(stackTag);
				list.appendTag(stackTag);
			}
		}
		nbt.setTag("Items", list);
		this.storage.writeToNBT(nbt);
		nbt.setInteger("currentRF", this.storage.getEnergyStored());
		nbt.setInteger("maxRF", this.maxRF);
		nbt.setInteger("cooldown", this.cooldown);
		nbt.setInteger("ipt", this.increasePerTick);

		if (this.hasCustomName()) {
			nbt.setString("CustomName", this.getCustomName());
		}
		return nbt;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		NBTTagList list = nbt.getTagList("Items", 10);
		for (int i = 0; i < list.tagCount(); ++i) {
			NBTTagCompound stackTag = list.getCompoundTagAt(i);
			int slot = stackTag.getByte("Slot") & 255;
			this.setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(stackTag));
		}
		this.cooldown = nbt.getInteger("cooldown");
		this.storage.readFromNBT(nbt);
		this.storage.setEnergyStored(nbt.getInteger("currentRF"));
		this.maxRF = nbt.getInteger("maxRF");
		this.increasePerTick = nbt.getInteger("ipt");

		if (nbt.hasKey("CustomName", 8)) {
			this.setCustomName(nbt.getString("CustomName"));
		}
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		ItemStack stack = this.getStackInSlot(index);
		this.setInventorySlotContents(index, null);
		return stack;
	}

	@Override
	public boolean canConnectEnergy(EnumFacing from) {
		return true;
	}

	@Override
	public void update() {
		if (this.worldObj != null) {
			if(!worldObj.isBlockPowered(pos)) {
				if(this.storage.getEnergyStored() < this.storage.getMaxEnergyStored()) {
					if (canUse()) {
						if (this.cooldown <= 0) {
							this.increasePerTick = getIncreasePerTickFromStack(this.inventory[0]);
							this.cooldown = getCooldownFromStack(this.inventory[0]);
							this.inventory[0].stackSize--;
							if (this.inventory[0].stackSize == 0) {
								this.inventory[0] = null;
							}
						}
					}
					if (this.cooldown > 0) {
						this.cooldown--;
						if (this.storage.getEnergyStored() < this.storage.getMaxEnergyStored()) {
							this.addEnergy(this.increasePerTick);
						}
					} else {
						this.increasePerTick = 0;
					}
					markDirty();
				}
				transferPower();
			}
		}
	}

	private boolean canUse() {
		EnumHandler.GeneratorTypes type = (EnumHandler.GeneratorTypes) this.getWorld().getBlockState(this.pos)
				.getValue(BlockGenerator.TYPE);
		if (this.inventory[0] == null) {
			return false;
		} else {
			if (type == EnumHandler.GeneratorTypes.FURNACE) {
				return TileEntityFurnace.isItemFuel(this.inventory[0]);
			}
			if (type == EnumHandler.GeneratorTypes.FOOD) {
				return this.inventory[0].getItem() instanceof ItemFood;
			}
			if (type == EnumHandler.GeneratorTypes.REDSTONE) {
				return this.inventory[0].getItem() == Items.REDSTONE
						|| this.inventory[0].getItem() == Item.getItemFromBlock(Blocks.REDSTONE_BLOCK);
			}
		}
		return false;
	}

	public static int getCooldownFromStack(ItemStack stack) {
		if (stack == null) {
			return 0;
		}
		if (TileEntityFurnace.isItemFuel(stack)) {
			return TileEntityFurnace.getItemBurnTime(stack) / 10;
		}
		if (stack.getItem() instanceof ItemFood) {
			ItemFood food = (ItemFood) stack.getItem();
			return Math.round(food.getSaturationModifier(stack) / food.getHealAmount(stack) * 900);
		}
		if (stack.getItem() == Items.REDSTONE || stack.getItem() == Item.getItemFromBlock(Blocks.REDSTONE_BLOCK)) {
			return stack.getItem() == Items.REDSTONE ? 70 : 600;
		}
		return 0;
	}

	public static int getCooldownFromStack(ItemStack stack, EnumHandler.GeneratorTypes type) {
		if (stack == null) {
			return 0;
		}
		if (TileEntityFurnace.isItemFuel(stack) && type == EnumHandler.GeneratorTypes.FURNACE) {
			return TileEntityFurnace.getItemBurnTime(stack) / 10;
		}
		if (stack.getItem() instanceof ItemFood && type == EnumHandler.GeneratorTypes.FOOD) {
			ItemFood food = (ItemFood) stack.getItem();
			return Math.round(food.getSaturationModifier(stack) / food.getHealAmount(stack) * 900);
		}
		if ((stack.getItem() == Items.REDSTONE || stack.getItem() == Item.getItemFromBlock(Blocks.REDSTONE_BLOCK))
				&& type == EnumHandler.GeneratorTypes.REDSTONE) {
			return stack.getItem() == Items.REDSTONE ? 70 : 600;
		}
		return 0;
	}

	public static int getIncreasePerTickFromStack(ItemStack stack) {
		if (stack == null) {
			return 0;
		}
		if (TileEntityFurnace.isItemFuel(stack)) {
			if (TileEntityFurnace.getItemBurnTime(stack) < 2000) {
				return TileEntityFurnace.getItemBurnTime(stack) / 50;
			}
			return TileEntityFurnace.getItemBurnTime(stack) / 200;
		}
		if (stack.getItem() instanceof ItemFood) {
			ItemFood food = (ItemFood) stack.getItem();
			return 4 * food.getHealAmount(stack);
		}
		if (stack.getItem() == Items.REDSTONE || stack.getItem() == Item.getItemFromBlock(Blocks.REDSTONE_BLOCK)) {
			return stack.getItem() == Items.REDSTONE ? 20 : 50;
		}
		return 0;
	}

	public static int getIncreasePerTickFromStack(ItemStack stack, EnumHandler.GeneratorTypes type) {
		if (stack == null) {
			return 0;
		}
		if (TileEntityFurnace.isItemFuel(stack) && type == EnumHandler.GeneratorTypes.FURNACE) {
			if (stack.getItem() == Items.COAL) {
				return TileEntityFurnace.getItemBurnTime(stack) / 100 * 2;
			}
			return TileEntityFurnace.getItemBurnTime(stack) / 100;
		}
		if (stack.getItem() instanceof ItemFood && type == EnumHandler.GeneratorTypes.FOOD) {
			ItemFood food = (ItemFood) stack.getItem();
			return 4 * food.getHealAmount(stack);
		}
		if ((stack.getItem() == Items.REDSTONE || stack.getItem() == Item.getItemFromBlock(Blocks.REDSTONE_BLOCK))
				&& type == EnumHandler.GeneratorTypes.REDSTONE) {
			return stack.getItem() == Items.REDSTONE ? 20 : 50;
		}
		return 0;
	}

	public static int getRFFromStack(ItemStack stack) {
		if (stack == null) {
			return 0;
		}
		int increasePerTick = getIncreasePerTickFromStack(stack);
		int cooldown = getCooldownFromStack(stack);
		return cooldown * increasePerTick;
	}

	public static int getRFFromStack(ItemStack stack, EnumHandler.GeneratorTypes type) {
		if (stack == null) {
			return 0;
		}
		int increasePerTick = getIncreasePerTickFromStack(stack, type);
		int cooldown = getCooldownFromStack(stack, type);
		return cooldown * increasePerTick;
	}

	public static boolean isStackFuel(ItemStack stack) {
		if (stack == null) {
			return false;
		}
		return getCooldownFromStack(stack) > 0;
	}

	public static boolean isStackFuel(ItemStack stack, EnumHandler.GeneratorTypes type) {
		if (stack == null) {
			return false;
		}
		return getCooldownFromStack(stack, type) > 0;
	}
	
	public void addEnergy(int value) {
		this.storage.setEnergyStored(this.storage.getEnergyStored() + value);
	}

	@Override
	public int getEnergyStored(EnumFacing from) {
		return this.storage.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(EnumFacing from) {
		return this.storage.getMaxEnergyStored();
	}

	@Override
	public int extractEnergy(EnumFacing from, int maxExtract, boolean simulate) {
		return this.storage.extractEnergy(maxExtract, simulate);
	}

}
