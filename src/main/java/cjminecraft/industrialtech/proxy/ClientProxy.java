package cjminecraft.industrialtech.proxy;

import com.sun.webkit.event.WCChangeListener;

import cjminecraft.industrialtech.IndustrialTech;
import cjminecraft.industrialtech.Reference;
import cjminecraft.industrialtech.client.gui.GuiHandler;
import cjminecraft.industrialtech.init.ModArmour;
import cjminecraft.industrialtech.init.ModBlocks;
import cjminecraft.industrialtech.init.ModItems;
import cjminecraft.industrialtech.init.ModTools;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void init() {
		NetworkRegistry.INSTANCE.registerGuiHandler(IndustrialTech.instance, new GuiHandler());
	}
	
	@Override
	public void registerRender() {
		ModItems.registerRenders();
		ModBlocks.registerRenders();
		ModArmour.registerRenders();
		ModTools.registerRenders();
	}
	
	@Override
	public void registerModelBakeryVariants() {
		ModelBakery.registerItemVariants(ModItems.ingot, new ResourceLocation(Reference.MODID, "copper_ingot"), new ResourceLocation(Reference.MODID, "silver_ingot"), new ResourceLocation(Reference.MODID, "tin_ingot"), new ResourceLocation(Reference.MODID, "zinc_ingot"), new ResourceLocation(Reference.MODID, "lead_ingot"), new ResourceLocation(Reference.MODID, "nickel_ingot"));
		ModelBakery.registerItemVariants(ModItems.nugget, new ResourceLocation(Reference.MODID, "copper_nugget"), new ResourceLocation(Reference.MODID, "silver_nugget"), new ResourceLocation(Reference.MODID, "tin_nugget"), new ResourceLocation(Reference.MODID, "zinc_nugget"), new ResourceLocation(Reference.MODID, "lead_nugget"), new ResourceLocation(Reference.MODID, "nickel_nugget"));
		ModelBakery.registerItemVariants(Item.getItemFromBlock(ModBlocks.ore), new ResourceLocation(Reference.MODID, "copper_ore"), new ResourceLocation(Reference.MODID, "silver_ore"), new ResourceLocation(Reference.MODID, "tin_ore"), new ResourceLocation(Reference.MODID, "zinc_ore"), new ResourceLocation(Reference.MODID, "lead_ore"), new ResourceLocation(Reference.MODID, "nickel_ore"));
		ModelBakery.registerItemVariants(Item.getItemFromBlock(ModBlocks.metalCompressed), new ResourceLocation(Reference.MODID, "copper_block"), new ResourceLocation(Reference.MODID, "silver_block"), new ResourceLocation(Reference.MODID, "tin_block"), new ResourceLocation(Reference.MODID, "zinc_block"), new ResourceLocation(Reference.MODID, "lead_block"), new ResourceLocation(Reference.MODID, "nickel_block"));
		ModelBakery.registerItemVariants(ModItems.chip, new ResourceLocation(Reference.MODID, "chip_basic"), new ResourceLocation(Reference.MODID, "chip_advanced"));
	}

}
