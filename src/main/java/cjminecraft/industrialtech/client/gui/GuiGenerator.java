package cjminecraft.industrialtech.client.gui;

import java.util.ArrayList;
import java.util.List;

import cjminecraft.industrialtech.IndustrialTech;
import cjminecraft.industrialtech.Reference;
import cjminecraft.industrialtech.container.ContainerGenerator;
import cjminecraft.industrialtech.tileentity.TileEntityGenerator;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiGenerator extends GuiContainer {
	
	public static final ResourceLocation texture = new ResourceLocation(Reference.MODID, "textures/gui/container/generator.png");
	
	private IInventory playerInv;
	private TileEntityGenerator te;
	
	public GuiGenerator(IInventory playerInv, TileEntityGenerator te) {
		super(new ContainerGenerator(playerInv, te));
		
		this.te = te;
		this.playerInv = playerInv;
		
		this.xSize = 176;
		this.ySize = 168;
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.getTextureManager().bindTexture(texture);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String s = this.te.getDisplayName().getUnformattedText();
		this.fontRendererObj.drawString(s, 88 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
		this.fontRendererObj.drawString(this.playerInv.getDisplayName().getUnformattedText(), 8, 75, 4210752);
		
		this.mc.getTextureManager().bindTexture(texture);
		this.drawTexturedModalRect(145, 20, 180, 21, 21, 51 - getRFProgressLevel(51));
		
		int width = (this.width - this.xSize) / 2;
		int height = (this.height - this.ySize) / 2;
		int actualMouseX = mouseX - width;
		int actualMouseY = mouseY - height;
		
		if(this.te.getField(2) >= this.te.getField(3) || this.te.getField(0) <= 0) {
			this.fontRendererObj.drawString(IndustrialTech.lang.localize("rftick") + ": 0", 36, 25, 900000);
		}
		else {
			this.fontRendererObj.drawString(IndustrialTech.lang.localize("rftick") + ": " + this.te.getField(1), 36, 25, 900000);
		}
		this.fontRendererObj.drawString(IndustrialTech.lang.localize("cooldown") + ": " + this.te.getField(0), 36, 36, 500000);
		this.fontRendererObj.drawString(IndustrialTech.lang.localize("timeleft") + ": " + (this.te.getField(0)/20) + "s", 36, 47, 600000);
		
		if(actualMouseX >= 144 && actualMouseX <= 166 && actualMouseY >= 19 && actualMouseY <= 72) {
			List<String> text = new ArrayList<String>();
			text.add(this.te.getField(2) + IndustrialTech.lang.localize("rf") + "/" + this.te.getField(3) + IndustrialTech.lang.localize("rf"));
			this.drawHoveringText(text, actualMouseX, actualMouseY);
		}
		
	}
	
	private int getRFProgressLevel(int progressIndicatorPixelHeight) {
		int rf = this.te.getField(2);
		int maxRF = this.te.getField(3);
		return maxRF != 0 && rf != 0 ? (rf * progressIndicatorPixelHeight) / maxRF : 0;
	}
	
	public TileEntityGenerator getTe() {
		return te;
	}

}
