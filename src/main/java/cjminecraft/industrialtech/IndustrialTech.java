package cjminecraft.industrialtech;

import cjminecraft.industrialtech.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;


@Mod(modid = IndustrialTech.MODID, name = IndustrialTech.NAME, version = IndustrialTech.VERSION, customProperties = {@Mod.CustomProperty(k = "useVersionChecker", v = "false")}, useMetadata = true)
public class IndustrialTech {

    public static final String MODID = "industrialtech";
    public static final String NAME = "IndustrialTech";
    public static final String VERSION = "${version}";
    public static final String SERVER_PROXY_CLASS = "cjminecraft.industrialtech.proxy.ServerProxy";
    public static final String CLIENT_PROXY_CLASS = "cjminecraft.industrialtech.proxy.ClientProxy";

    @Mod.Instance
    public static IndustrialTech instance;

    @SidedProxy(serverSide = SERVER_PROXY_CLASS, clientSide = CLIENT_PROXY_CLASS, modId = MODID)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event) {

    }

    @Mod.EventHandler
    public static void init(FMLInitializationEvent event) {

    }

    @Mod.EventHandler
    public static void postInit(FMLPostInitializationEvent event) {

    }

}
