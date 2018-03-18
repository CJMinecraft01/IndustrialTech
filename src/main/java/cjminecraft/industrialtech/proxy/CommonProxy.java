package cjminecraft.industrialtech.proxy;

import cjminecraft.core.proxy.IProxy;
import cjminecraft.industrialtech.IndustrialTech;
import cjminecraft.industrialtech.client.gui.GuiHandler;
import cjminecraft.industrialtech.tiles.TileEntityPhotonGenerator;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy implements IProxy {

    @Override
    public void preInit() {
    }

    @Override
    public void init() {
        NetworkRegistry.INSTANCE.registerGuiHandler(IndustrialTech.instance, new GuiHandler());
    }

    @Override
    public void postInit() {

    }
}
