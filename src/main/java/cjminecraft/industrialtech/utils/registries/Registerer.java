package cjminecraft.industrialtech.utils.registries;

import cjminecraft.industrialtech.IndustrialTech;
import cjminecraft.industrialtech.init.ITBlocks;
import cjminecraft.industrialtech.init.ITItems;
import cjminecraft.industrialtech.utils.registries.annotations.RegisterItem;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.lang.reflect.Field;

@Mod.EventBusSubscriber
public class Registerer {

    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        IndustrialTech.LOGGER.info("Searching for items to register");
        int registeredItems = 0;
        try {
            for (Field field : ITItems.class.getDeclaredFields()) {
                if (field.isAnnotationPresent(RegisterItem.class) && field.getType().isAssignableFrom(Item.class)) {
                    RegisterItem details = field.getAnnotation(RegisterItem.class);
                    Item item = (Item) field.get(null);
                    if (item == null) {
                        item = (Item) field.getType().newInstance();
                        field.set(null, item);
                    }
                    item.setRegistryName(new ResourceLocation(IndustrialTech.MODID, details.registryName()));
                    if (!details.unlocalizedName().isEmpty())
                        item.setUnlocalizedName(details.unlocalizedName());
                    else
                        item.setUnlocalizedName(details.registryName());
                    event.getRegistry().register(item);
                    registeredItems++;
                }
            }

            for (Field field : ITBlocks.class.getDeclaredFields()) {
                if (field.isAnnotationPresent(RegisterItem.class) && field.getType().isAssignableFrom(Block.class)) {
                    RegisterItem details = field.getAnnotation(RegisterItem.class);
                    Block block = (Block) field.get(null);
                    if (block == null) {
                        block = (Block) field.getType().newInstance();
                        field.set(null, block);
                    }

                    ItemBlock item;
                    try {
                        Class clazz = Class.forName(details.itemBlockClassName());
                        item = (ItemBlock) clazz.getDeclaredConstructor(Block.class).newInstance(block);
                    } catch (ClassNotFoundException e) {
                        IndustrialTech.LOGGER.warn("Could not register custom item block as class did not exist. " + details.itemBlockClassName());
                        continue;
                    }

                    item.setRegistryName(new ResourceLocation(IndustrialTech.MODID, details.registryName()));
                    if (!details.unlocalizedName().isEmpty())
                        item.setUnlocalizedName(details.unlocalizedName());
                    else
                        item.setUnlocalizedName(details.registryName());
                    event.getRegistry().register(item);
                    registeredItems++;
                }
            }
        } catch (Exception e) {
            IndustrialTech.LOGGER.catching(e);
        }
        IndustrialTech.LOGGER.info("Successfully registered " + registeredItems + " items!");
    }

}
