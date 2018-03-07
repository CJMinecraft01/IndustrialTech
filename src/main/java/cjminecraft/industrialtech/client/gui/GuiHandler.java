package cjminecraft.industrialtech.client.gui;

import cjminecraft.industrialtech.container.ContainerPhotonGenerator;
import cjminecraft.industrialtech.tiles.TileEntityPhotonGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;

public class GuiHandler implements IGuiHandler{

    public static final int PHOTON_GENERATOR = 0;

    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(pos);
        switch(ID) {
            case PHOTON_GENERATOR:
                return new ContainerPhotonGenerator(player.inventory, (TileEntityPhotonGenerator) te);
        }
        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(pos);
        switch(ID) {
            case PHOTON_GENERATOR:
                return new GuiPhotonGenerator(player.inventory, (TileEntityPhotonGenerator) te);
        }
        return null;
    }
}
