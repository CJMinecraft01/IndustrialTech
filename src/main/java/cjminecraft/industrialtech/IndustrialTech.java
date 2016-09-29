package cjminecraft.industrialtech;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cjminecraft.industrialtech.creativetabs.TabITBlocks;
import cjminecraft.industrialtech.creativetabs.TabITItems;
import cjminecraft.industrialtech.handlers.AchievementHandler;
import cjminecraft.industrialtech.handlers.OreDictHandler;
import cjminecraft.industrialtech.handlers.RecipeHandler;
import cjminecraft.industrialtech.init.ModArmour;
import cjminecraft.industrialtech.init.ModBlocks;
import cjminecraft.industrialtech.init.ModItems;
import cjminecraft.industrialtech.init.ModTools;
import cjminecraft.industrialtech.proxy.CommonProxy;
import cjminecraft.industrialtech.util.Lang;
import cjminecraft.industrialtech.util.ModChecker;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.FMLEventChannel;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod(modid = Reference.MODID, version = Reference.VERSION, name = Reference.NAME, dependencies = "after:Waila")
public class IndustrialTech
{
	
	cjminecraft.industrialtech.handlers.EventHandler eventHandler = new cjminecraft.industrialtech.handlers.EventHandler();
	
	public static final Lang lang = new Lang(Reference.MODID);
	
	public static CreativeTabs blocks = new TabITBlocks();
	public static CreativeTabs items = new TabITItems();
	
	@Mod.Instance(Reference.MODID)
	public static IndustrialTech instance = new IndustrialTech();
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ModItems.init();
		ModBlocks.init();
		ModArmour.init();
		ModTools.init();
		ModItems.register();
		ModBlocks.register();
		ModArmour.register();
		ModTools.register();
		proxy.registerTileEntities();
    	proxy.registerRender();
    	
    	AchievementHandler.registerAchievements();
    	
    	FMLInterModComms.sendMessage("Waila", "register", "cjminecraft.industrialtech.waila.Waila.load");
	}
	
    @EventHandler
    public void init(FMLInitializationEvent event) {
    	proxy.registerModelBakeryVariants();
    	proxy.init();
    	
    	eventHandler.registerEvents();
    	
    	OreDictHandler.registerOreDictionary();
    	RecipeHandler.registerCraftingRecipes();
    	RecipeHandler.registerFurnaceRecipes();
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	ModChecker.checkMods();
    }
}
