package cjminecraft.industrialtech.blocks.item;

import java.util.List;

import org.lwjgl.input.Keyboard;

import cjminecraft.industrialtech.IndustrialTech;
import cjminecraft.industrialtech.handlers.EnumHandler;
import net.minecraft.block.Block;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

public class ItemBlockMeta extends ItemBlock {

	public ItemBlockMeta(Block block) {
		super(block);
		if (!(block instanceof IMetaBlockName)) {
			throw new IllegalArgumentException(String
					.format("The given Block %s is not an instance of ISpecialBlockName!", block.getUnlocalizedName()));
		}
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		if (stack.getUnlocalizedName().contains("metal_compressed")) {
			tooltip.add(TextFormatting.GRAY + IndustrialTech.lang.localize("metal.beacon"));
		}
		if(!stack.getUnlocalizedName().contains("ore")) {
			String[] displayName = stack.getDisplayName().split(" ");
			String itemName = displayName[1];
			for (int i = 0; i < EnumHandler.CompressedTypes.values().length; i++) {
				if (stack.getItemDamage() == i) {
					if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
						int number = ((int) Math.pow(9, EnumHandler.CompressedTypes.values()[i].getID() + 1))
								* stack.stackSize;
						if (stack.stackSize > 49 && i == 7) {
							tooltip.add(TextFormatting.GRAY + IndustrialTech.lang.localize("compressed.stackInfo1")
									+ TextFormatting.OBFUSCATED + number + TextFormatting.RESET + TextFormatting.GRAY + " "
									+ itemName);
							tooltip.add(TextFormatting.GRAY + IndustrialTech.lang.localize("compressed.stackInfoClose1")
									+ TextFormatting.YELLOW + "<SHIFT>" + TextFormatting.GRAY
									+ IndustrialTech.lang.localize("compressed.stackInfoClose2"));
						} else {
							tooltip.add(TextFormatting.GRAY + IndustrialTech.lang.localize("compressed.stackInfo1") + number
									+ " " + itemName);
							tooltip.add(TextFormatting.GRAY + IndustrialTech.lang.localize("compressed.stackInfoClose1")
									+ TextFormatting.YELLOW + "<SHIFT>" + TextFormatting.GRAY
									+ IndustrialTech.lang.localize("compressed.stackInfoClose2"));
						}
					} else {
						tooltip.add(TextFormatting.GRAY + IndustrialTech.lang.localize("compressed.stackInfo2")
								+ ((int) Math.pow(9, EnumHandler.CompressedTypes.values()[i].getID() + 1)) + " "
								+ itemName);
						tooltip.add(TextFormatting.GRAY + IndustrialTech.lang.localize("compressed.showStackInfo1")
								+ TextFormatting.YELLOW + "<SHIFT>" + TextFormatting.GRAY
								+ IndustrialTech.lang.localize("compressed.showStackInfo2"));
					}
				}
			}
		}
	}

	public int getMetadata(int damage) {
		return damage;
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName(stack) + "." + ((IMetaBlockName) this.block).getSpecialName(stack);
	}

}
