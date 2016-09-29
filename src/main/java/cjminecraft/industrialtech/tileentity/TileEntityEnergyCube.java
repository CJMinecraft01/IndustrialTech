package cjminecraft.industrialtech.tileentity;

import cjminecraft.industrialtech.util.TileEntityEnergyHandler;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

public class TileEntityEnergyCube extends TileEntityEnergyHandler implements IEnergyProvider, IEnergyReceiver {
	
	public TileEntityEnergyCube() {
		super(1000000, 1000, 1000);
	}

	@Override
	public void update() {
		if(this.worldObj != null) {
			transferPower();
			receivePower();
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		return super.writeToNBT(nbt);
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
	public boolean canConnectEnergy(EnumFacing from) {
		return true;
	}

	@Override
	public int receiveEnergy(EnumFacing from, int maxReceive, boolean simulate) {
		return this.storage.receiveEnergy(maxReceive, simulate);
	}

	@Override
	public int extractEnergy(EnumFacing from, int maxExtract, boolean simulate) {
		return this.storage.extractEnergy(maxExtract, simulate);
	}

}
