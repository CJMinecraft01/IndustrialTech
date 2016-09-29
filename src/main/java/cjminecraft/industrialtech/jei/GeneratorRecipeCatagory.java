package cjminecraft.industrialtech.jei;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.annotation.Nonnull;

import com.google.common.collect.Lists;

import cjminecraft.industrialtech.IndustrialTech;
import cjminecraft.industrialtech.client.gui.GuiGenerator;
import cjminecraft.industrialtech.container.ContainerGenerator;
import cjminecraft.industrialtech.init.ModBlocks;
import cjminecraft.industrialtech.tileentity.TileEntityGenerator;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.recipe.BlankRecipeCategory;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameData;

public class GeneratorRecipeCatagory extends BlankRecipeCategory<GeneratorRecipeCatagory.GeneratorRecipeWrapper> {

	public static final @Nonnull String UID = "Generator";
	
	public static class GeneratorRecipeWrapper extends BlankRecipeWrapper {
		
		private ItemStack input;
		
		public GeneratorRecipeWrapper(ItemStack input) {
			this.input = input;
		}
		
		public int getRFProduced() {
			return TileEntityGenerator.getRFFromStack(input);
		}
		
		public int getCooldown() {
			return TileEntityGenerator.getCooldownFromStack(input);
		}
		
		public int getIncreasePerTick() {
			return TileEntityGenerator.getIncreasePerTickFromStack(input);
		}
		
		@Override
		public List<ItemStack> getInputs() {
			List<ItemStack> inputs = new ArrayList<ItemStack>();
			Iterator<Entry<ResourceLocation, Item>> itemIterator = GameData.getItemRegistry().getEntries().iterator();
			while(itemIterator.hasNext()) {
				Entry<ResourceLocation, Item> entry = itemIterator.next();
				ItemStack stack = new ItemStack(entry.getValue());
				if(TileEntityGenerator.isStackFuel(stack)) {
					inputs.add(stack);
				}
			}
			Iterator<Entry<ResourceLocation, Block>> blockIterator = GameData.getBlockRegistry().getEntries().iterator();
			while(blockIterator.hasNext()) {
				Entry<ResourceLocation, Block> entry = blockIterator.next();
				ItemStack stack = new ItemStack(entry.getValue());
				if(TileEntityGenerator.isStackFuel(stack)) {
					inputs.add(stack);
				}
			}
			return inputs;
		}
		
		@Override
		public List<ItemStack> getOutputs() {
			return new ArrayList<ItemStack>();
		}
		
		@Override
		public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
			int rfProduced = TileEntityGenerator.getRFFromStack(input);
			int cooldown = TileEntityGenerator.getCooldownFromStack(input);
			int increasePerTick = TileEntityGenerator.getIncreasePerTickFromStack(input);
			
			minecraft.fontRendererObj.drawString(IndustrialTech.lang.localize("rftick") + ": " + increasePerTick, 36, 25, 900000);
			minecraft.fontRendererObj.drawString(IndustrialTech.lang.localize("duration") + ": " + (cooldown / 20) + "s", 36, 36, 500000);
			minecraft.fontRendererObj.drawString(IndustrialTech.lang.localize("rfproduced") + ": " + rfProduced, 36, 47, 600000);
			
			GlStateManager.color(1,1,1,1);
		}
		
	}
	
	public static void register(IModRegistry registry, IGuiHelper guiHelper) {
		registry.addRecipeCategories(new GeneratorRecipeCatagory(guiHelper));
		registry.addRecipeHandlers(new BaseRecipeHandler<GeneratorRecipeWrapper>(GeneratorRecipeWrapper.class, GeneratorRecipeCatagory.UID));
		registry.addRecipeCategoryCraftingItem(new ItemStack(ModBlocks.generator), GeneratorRecipeCatagory.UID);
		
		List<GeneratorRecipeWrapper> result = new ArrayList<GeneratorRecipeWrapper>();
		Iterator<Entry<ResourceLocation, Item>> itemIterator = GameData.getItemRegistry().getEntries().iterator();
		while(itemIterator.hasNext()) {
			Entry<ResourceLocation, Item> entry = itemIterator.next();
			ItemStack stack = new ItemStack(entry.getValue());
			if(TileEntityGenerator.isStackFuel(stack)) {
				result.add(new GeneratorRecipeWrapper(stack));
			}
		}
		Iterator<Entry<ResourceLocation, Block>> blockIterator = GameData.getBlockRegistry().getEntries().iterator();
		while(blockIterator.hasNext()) {
			Entry<ResourceLocation, Block> entry = blockIterator.next();
			ItemStack stack = new ItemStack(entry.getValue());
			if(TileEntityGenerator.isStackFuel(stack)) {
				result.add(new GeneratorRecipeWrapper(stack));
			}
		}
		registry.getRecipeTransferRegistry().addRecipeTransferHandler(ContainerGenerator.class, GeneratorRecipeCatagory.UID, 0, 1, 0, 1);
	}

	@Nonnull
	private final IDrawable background;
	
	@Nonnull
	protected final IDrawableAnimated rfbar;
	
	private GeneratorRecipeWrapper currentRecipe;
	
	public GeneratorRecipeCatagory(IGuiHelper guiHelper) {
		background = guiHelper.createDrawable(GuiGenerator.texture, 0, 0, 176, 168);
		
		rfbar = guiHelper.createAnimatedDrawable(guiHelper.createDrawable(GuiGenerator.texture, 180, 21, 21, 51), 50, IDrawableAnimated.StartDirection.TOP, false);
	}

	@Override
	public String getUid() {
		return UID;
	}

	@Override
	public String getTitle() {
		String unlocalizedName = ModBlocks.generator.getUnlocalizedName().substring(5);
		return unlocalizedName != null ? unlocalizedName : "ERROR";
	}

	@Override
	public IDrawable getBackground() {
		return background;
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, GeneratorRecipeWrapper recipeWrapper) {
		currentRecipe = recipeWrapper;
		
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
		guiItemStacks.init(0, true, 8, 20);
		
		final ItemStack input = currentRecipe.input;
		guiItemStacks.set(0, input);
	}
	
	
	
}
