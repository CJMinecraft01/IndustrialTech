package cjminecraft.industrialtech.tileentity;

import java.util.ArrayList;
import java.util.List;

import cjminecraft.industrialtech.util.IndustrialTechTileEntity;
import cjminecraft.industrialtech.util.ModChecker;
import cjminecraft.industrialtech.util.Utils.CompatableMods;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyConnection;
import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;
import cofh.api.energy.IEnergyStorage;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.MathHelper;

public class TileEntityEnergyPipe extends IndustrialTechTileEntity implements IEnergyProvider, IEnergyReceiver, ITickable {
	
	private EnergyStorage storage = new EnergyStorage(1000, 240, 240);

	@Override
	public boolean canConnectEnergy(EnumFacing from) {
		return true;
	}

	@Override
	public void update() {
		if(this.worldObj != null) {
			receivePower();
			transferPower();
		}
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		return this.storage.writeToNBT(nbt);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.storage.readFromNBT(nbt);
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

	@Override
	public int receiveEnergy(EnumFacing from, int maxReceive, boolean simulate) {
		return this.storage.receiveEnergy(maxReceive, simulate);
	}
	
	public void addEnergy(int value) {
		this.storage.setEnergyStored(this.storage.getEnergyStored() + value);
	}
	
	List<TileEntity> faces = new ArrayList<TileEntity>();
	TileEntity north;
	TileEntity south;
	TileEntity east;
	TileEntity west;
	TileEntity up;
	TileEntity down;
	
	private void transferPower() {
		if(this.storage.getEnergyStored() > 0) {
			north = this.worldObj.getTileEntity(pos.north());
			south = this.worldObj.getTileEntity(pos.south());
			east = this.worldObj.getTileEntity(pos.east());
			west = this.worldObj.getTileEntity(pos.west());
			up = this.worldObj.getTileEntity(pos.up());
			down = this.worldObj.getTileEntity(pos.down());
			if (north != null) {
				faces.add(north);
			}
			if (south != null) {
				faces.add(south);
			}
			if (east != null) {
				faces.add(east);
			}
			if (west != null) {
				faces.add(west);
			}
			if (up != null) {
				faces.add(up);
			}
			if (down != null) {
				faces.add(down);
			}
			if (faces.size() != 0) {
				int clampValue = this.storage.getMaxExtract() / faces.size();
				int transferPerSide = MathHelper.clamp_int(clampValue, 0, this.storage.getMaxExtract());
				transferPerSide = MathHelper.clamp_int(transferPerSide, 0, this.storage.getEnergyStored() / faces.size());
				if (faces.size() == 6) {
					transferPowerToTileEntity(faces.get(5), transferPerSide);
					transferPowerToTileEntity(faces.get(4), transferPerSide);
					transferPowerToTileEntity(faces.get(3), transferPerSide);
					transferPowerToTileEntity(faces.get(2), transferPerSide);
					transferPowerToTileEntity(faces.get(1), transferPerSide);
					transferPowerToTileEntity(faces.get(0), transferPerSide);
				}
				if (faces.size() == 5) {
					transferPowerToTileEntity(faces.get(4), transferPerSide);
					transferPowerToTileEntity(faces.get(3), transferPerSide);
					transferPowerToTileEntity(faces.get(2), transferPerSide);
					transferPowerToTileEntity(faces.get(1), transferPerSide);
					transferPowerToTileEntity(faces.get(0), transferPerSide);
				}
				if (faces.size() == 4) {
					transferPowerToTileEntity(faces.get(3), transferPerSide);
					transferPowerToTileEntity(faces.get(2), transferPerSide);
					transferPowerToTileEntity(faces.get(1), transferPerSide);
					transferPowerToTileEntity(faces.get(0), transferPerSide);
				}
				if (faces.size() == 3) {
					transferPowerToTileEntity(faces.get(2), transferPerSide);
					transferPowerToTileEntity(faces.get(1), transferPerSide);
					transferPowerToTileEntity(faces.get(0), transferPerSide);
				}
				if (faces.size() == 2) {
					transferPowerToTileEntity(faces.get(1), transferPerSide);
					transferPowerToTileEntity(faces.get(0), transferPerSide);
				}
				if (faces.size() == 1) {
					transferPowerToTileEntity(faces.get(0), transferPerSide);
				}
			}
			faces.clear();
			markDirty();
		}
	}
	
	private void transferPowerToTileEntity(TileEntity tileentity, int transfer) {
		if (tileentity instanceof IEnergyReceiver) {
			IEnergyReceiver te = (IEnergyReceiver) tileentity;
			if (te.getEnergyStored(null) < te.getMaxEnergyStored(null)) {
				te.receiveEnergy(EnumFacing.SOUTH, transfer, false);
				this.storage.extractEnergy(transfer, false);
			}
		} else if (tileentity instanceof IEnergyStorage) {
			IEnergyStorage te = (IEnergyStorage) tileentity;
			if (te.getEnergyStored() < te.getMaxEnergyStored()) {
				te.receiveEnergy(transfer, false);
				this.storage.extractEnergy(transfer, false);
			}
		} else {
			if (ModChecker.isModLoaded(CompatableMods.ENDERIO) || ModChecker.isModLoaded(CompatableMods.MEKANISM)) { // Adds power to ender io
												// machines
				Class clazz = tileentity.getClass();
				try {
					if (clazz.getMethod("getEnergyStored", EnumFacing.class).invoke(clazz,
							EnumFacing.NORTH) instanceof Integer) {
						if ((Integer) clazz.getMethod("getEnergyStored", EnumFacing.class).invoke(clazz,
								EnumFacing.NORTH) < (Integer) clazz.getMethod("getMaxEnergyStored", EnumFacing.class)
										.invoke(clazz, EnumFacing.NORTH)) {
							clazz.getMethod("addEnergy", Integer.class).invoke(clazz, transfer);
							this.storage.extractEnergy(transfer, false);
						}
					}
				} catch (Exception e) {

				}
			}
		}
	}
	
	private void receivePower() {
		if(this.storage.getEnergyStored() > 0) {
			north = this.worldObj.getTileEntity(pos.north());
			south = this.worldObj.getTileEntity(pos.south());
			east = this.worldObj.getTileEntity(pos.east());
			west = this.worldObj.getTileEntity(pos.west());
			up = this.worldObj.getTileEntity(pos.up());
			down = this.worldObj.getTileEntity(pos.down());
			if (north != null) {
				faces.add(north);
			}
			if (south != null) {
				faces.add(south);
			}
			if (east != null) {
				faces.add(east);
			}
			if (west != null) {
				faces.add(west);
			}
			if (up != null) {
				faces.add(up);
			}
			if (down != null) {
				faces.add(down);
			}
			if (faces.size() != 0) {
				int clampValue = this.storage.getMaxReceive() / faces.size();
				int transferPerSide = MathHelper.clamp_int(clampValue, 0, this.storage.getMaxReceive());
				transferPerSide = MathHelper.clamp_int(transferPerSide, 0, this.storage.getEnergyStored() / faces.size());
				if (faces.size() == 6) {
					receivePowerFromTileEntity(faces.get(5), transferPerSide);
					receivePowerFromTileEntity(faces.get(4), transferPerSide);
					receivePowerFromTileEntity(faces.get(3), transferPerSide);
					receivePowerFromTileEntity(faces.get(2), transferPerSide);
					receivePowerFromTileEntity(faces.get(1), transferPerSide);
					receivePowerFromTileEntity(faces.get(0), transferPerSide);
				}
				if (faces.size() == 5) {
					receivePowerFromTileEntity(faces.get(4), transferPerSide);
					receivePowerFromTileEntity(faces.get(3), transferPerSide);
					receivePowerFromTileEntity(faces.get(2), transferPerSide);
					receivePowerFromTileEntity(faces.get(1), transferPerSide);
					receivePowerFromTileEntity(faces.get(0), transferPerSide);
				}
				if (faces.size() == 4) {
					receivePowerFromTileEntity(faces.get(3), transferPerSide);
					receivePowerFromTileEntity(faces.get(2), transferPerSide);
					receivePowerFromTileEntity(faces.get(1), transferPerSide);
					receivePowerFromTileEntity(faces.get(0), transferPerSide);
				}
				if (faces.size() == 3) {
					receivePowerFromTileEntity(faces.get(2), transferPerSide);
					receivePowerFromTileEntity(faces.get(1), transferPerSide);
					receivePowerFromTileEntity(faces.get(0), transferPerSide);
				}
				if (faces.size() == 2) {
					receivePowerFromTileEntity(faces.get(1), transferPerSide);
					receivePowerFromTileEntity(faces.get(0), transferPerSide);
				}
				if (faces.size() == 1) {
					receivePowerFromTileEntity(faces.get(0), transferPerSide);
				}
			}
			faces.clear();
			markDirty();
		}
	}
	
	private void receivePowerFromTileEntity(TileEntity tileentity, int transfer) {
		if(tileentity instanceof IEnergyReceiver) {
			return;
		}
		if (tileentity instanceof IEnergyProvider) {
			IEnergyProvider te = (IEnergyProvider) tileentity;
			if (te.getEnergyStored(null) < te.getMaxEnergyStored(null)) {
				te.extractEnergy(null, transfer, false);
				this.storage.receiveEnergy(transfer, false);
			}
		} else if (tileentity instanceof IEnergyStorage) {
			IEnergyStorage te = (IEnergyStorage) tileentity;
			if (te.getEnergyStored() < te.getMaxEnergyStored()) {
				te.extractEnergy(transfer, false);
				this.storage.receiveEnergy(transfer, false);
			}
		} else {
			if (ModChecker.isModLoaded(CompatableMods.ENDERIO) || ModChecker.isModLoaded(CompatableMods.MEKANISM)){ // Adds power to ender io
												// machines
				Class clazz = tileentity.getClass();
				try {
					if (clazz.getMethod("getEnergyStored", EnumFacing.class).invoke(clazz,
						EnumFacing.NORTH) instanceof Integer) {
						clazz.getMethod("addEnergy", Integer.class).invoke(clazz, -transfer);
						this.storage.receiveEnergy(transfer, false);
					}
				} catch (Exception e) {

				}
			}
		}
	}

}
