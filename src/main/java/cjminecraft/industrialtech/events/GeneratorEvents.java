package cjminecraft.industrialtech.events;

import cjminecraft.industrialtech.IndustrialTech;
import cjminecraft.industrialtech.client.gui.GuiGenerator;
import cjminecraft.industrialtech.handlers.EnumHandler.GeneratorTypes;
import cjminecraft.industrialtech.tileentity.TileEntityGenerator;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GeneratorEvents {
	
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onEvent(ItemTooltipEvent event) {
		if(Minecraft.getMinecraft().currentScreen instanceof GuiGenerator) {
			GuiGenerator gui = (GuiGenerator) Minecraft.getMinecraft().currentScreen;
			TileEntityGenerator te = gui.getTe();
			GeneratorTypes type = GeneratorTypes.values()[te.getBlockMetadata()];
			ItemStack stack = event.getItemStack();
			if(TileEntityGenerator.isStackFuel(stack, type)) {
				event.getToolTip().add(TextFormatting.GRAY + IndustrialTech.lang.localize("rfoutput") + ": " + TileEntityGenerator.getRFFromStack(stack) + IndustrialTech.lang.localize("rf"));
				event.getToolTip().add(TextFormatting.GRAY + IndustrialTech.lang.localize("rftick") + ": " + TileEntityGenerator.getIncreasePerTickFromStack(stack));
				event.getToolTip().add(TextFormatting.GRAY + IndustrialTech.lang.localize("duration") + ": " + TileEntityGenerator.getCooldownFromStack(stack)/20 + "s");
			}
		}
	}

}
