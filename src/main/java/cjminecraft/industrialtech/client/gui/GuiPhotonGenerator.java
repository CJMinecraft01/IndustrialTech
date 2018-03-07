package cjminecraft.industrialtech.client.gui;

import cjminecraft.core.client.gui.GuiBase;
import cjminecraft.core.client.gui.element.ElementEnergyBar;
import cjminecraft.industrialtech.IndustrialTech;
import cjminecraft.industrialtech.container.ContainerPhotonGenerator;
import cjminecraft.industrialtech.tiles.TileEntityPhotonGenerator;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiPhotonGenerator extends GuiBase {

    public static final ResourceLocation TEXTURE = new ResourceLocation(IndustrialTech.MODID,
            "textures/gui/container/photon_generator.png");

    private TileEntityPhotonGenerator te;

    public GuiPhotonGenerator(IInventory playerInv, TileEntityPhotonGenerator te) {
        super(new ContainerPhotonGenerator(playerInv, te), TEXTURE);
        this.te = te;
        setGuiSize(176, 166);
        this.name = "container.photon_generator";
    }

    @Override
    public void initGui() {
        super.initGui();
        IndustrialTech.LOGGER.info("NEW SIZE");
        addElement(new ElementEnergyBar(this, 7, 21, 18, 53).shouldSync(this.te.getPos(), null));
    }
}
