package cjminecraft.industrialtech.container;

import cjminecraft.industrialtech.tiles.TileEntityPhotonGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class ContainerPhotonGenerator extends Container {

    private TileEntityPhotonGenerator te;

    public ContainerPhotonGenerator(IInventory playerInv, TileEntityPhotonGenerator te) {
        this.te = te;

        // The player's inventory slots
        int xPos = 8; // The x position of the top left player inventory slot on our texture
        int yPos = 84; // The y position of the top left player inventory slot on our texture

        // Player slots
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 9; ++x) {
                this.addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, xPos + x * 18, yPos + y * 18));
            }
        }

        for (int x = 0; x < 9; ++x) {
            this.addSlotToContainer(new Slot(playerInv, x, xPos + x * 18, yPos + 58));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return player.getDistanceSq(this.te.getPos().add(0.5, 0.5, 0.5)) <= 64;
    }
}
