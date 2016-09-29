package cjminecraft.industrialtech.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cjminecraft.industrialtech.Reference;
import cjminecraft.industrialtech.handlers.EnumHandler.ConnectionTypes;
import cjminecraft.industrialtech.init.ModBlocks;
import cjminecraft.industrialtech.tileentity.TileEntityEnergyPipe;
import cjminecraft.industrialtech.tileentity.TileEntityGenerator;
import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.IEnergyReceiver;
import cofh.api.energy.IEnergyStorage;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

public class Utils {

	private static Logger logger;
	public static final int MAX_32BIT_INTEGER = 2147483647;

	public static Logger getLogger() {
		if (logger == null) {
			logger = LogManager.getFormatterLogger(Reference.MODID);
		}
		return logger;
	}
	
	public static IBlockState calculateConnectionType(TileEntity tileentity, IBlockState state, IProperty property) {
		if(tileentity == null) {
			return state.withProperty(property, ConnectionTypes.NONE);
		}
		if(tileentity instanceof TileEntityEnergyPipe) {
			return state.withProperty(property, ConnectionTypes.PIPE);
		}
		if(tileentity instanceof IEnergyHandler) {
			return state.withProperty(property, ConnectionTypes.BLOCK);
		}
		else {
			if (ModChecker.isModLoaded(CompatableMods.ENDERIO) || ModChecker.isModLoaded(CompatableMods.MEKANISM)) {
				Class clazz = tileentity.getClass();
				try {
					if (clazz.getMethod("getEnergyStored", EnumFacing.class).invoke(clazz,
							EnumFacing.NORTH) instanceof Integer) {
						return state.withProperty(property, ConnectionTypes.BLOCK);
					}
				} catch (Exception e) {

				}
			}
		}
		return state.withProperty(property, ConnectionTypes.NONE);
	}
	
	public static enum CompatableMods {
		ENDERIO("EnderIO"),
		MEKANISM("Mekanism");
		
		private String name;
		
		CompatableMods(String name) {
			this.name = name;
		}
		
		public String getName() {
			return name;
		}
		
	}

}
