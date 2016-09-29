package cjminecraft.industrialtech.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cjminecraft.industrialtech.util.Utils.CompatableMods;
import net.minecraftforge.fml.common.Loader;

public class ModChecker {
	
	private static HashMap<CompatableMods, Boolean> loadedMods = new HashMap<CompatableMods, Boolean>();
	
	public static void checkMods() {
		for(CompatableMods mod : CompatableMods.values()) {
			loadedMods.put(mod, checkMod(mod.getName()));
		}
	}
	
	public static boolean checkMod(String modid) {
		boolean loaded = Loader.isModLoaded(modid);
		if(loaded) {
			Utils.getLogger().info("Detected mod: " + modid);
		}
		return loaded;
	}
	
	public static HashMap<CompatableMods, Boolean> getLoadedMods() {
		return loadedMods;
	}
	
	public static boolean isModLoaded(CompatableMods mod) {
		if(loadedMods.containsKey(mod)) {
			return loadedMods.get(mod);
		}
		return false;
	}

}
