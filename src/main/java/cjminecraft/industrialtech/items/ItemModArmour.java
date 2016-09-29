package cjminecraft.industrialtech.items;

import java.util.Iterator;
import java.util.List;

import cjminecraft.industrialtech.IndustrialTech;
import cjminecraft.industrialtech.Reference;
import cjminecraft.industrialtech.handlers.AchievementHandler;
import cjminecraft.industrialtech.init.ModArmour;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ItemModArmour extends ItemArmor {

	public ItemModArmour(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn, String unlocalizedName, String registryName) {
		super(materialIn, renderIndexIn, equipmentSlotIn);
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(new ResourceLocation(Reference.MODID, registryName));
		this.setCreativeTab(IndustrialTech.items);
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		tooltip.add("Durability: "+ (stack.getMaxDamage() - stack.getItemDamage()) + "/" + stack.getMaxDamage());
	}
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
		int tinArmourPeices = 0;
		if(player.getArmorInventoryList() != null) {
			Iterator<ItemStack> iterator = player.getArmorInventoryList().iterator();
			while(iterator.hasNext()) {
				ItemStack stack = iterator.next();
				if(stack != null) {
					if(stack.getItem() instanceof ItemModArmour) {
						ItemModArmour item = (ItemModArmour) stack.getItem();
						if(item.getArmorMaterial() == ModArmour.tinMaterial) {
							tinArmourPeices++;
							continue;
						}
					}
				}
			}
		}
		if(tinArmourPeices == 4) {
			if(!player.hasAchievement(AchievementHandler.achievementTinMan)) {
				player.addStat(AchievementHandler.achievementTinMan);
			}
		}
	}

}
