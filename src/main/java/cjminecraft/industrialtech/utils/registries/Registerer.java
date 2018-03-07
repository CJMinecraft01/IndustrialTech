package cjminecraft.industrialtech.utils.registries;

import cjminecraft.industrialtech.IndustrialTech;
import cjminecraft.industrialtech.init.ITBlocks;
import cjminecraft.industrialtech.init.ITItems;
import cjminecraft.industrialtech.utils.registries.annotations.RegisterBlock;
import cjminecraft.industrialtech.utils.registries.annotations.RegisterItem;
import cjminecraft.industrialtech.utils.registries.annotations.RegisterItemBlock;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.Level;

import java.lang.reflect.Field;

@Mod.EventBusSubscriber
public class Registerer {

    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        IndustrialTech.LOGGER.info("Searching for items to register");
        int registeredItems = 0;
        for (Field field : ITItems.class.getDeclaredFields()) {
            if (field.isAnnotationPresent(RegisterItem.class)) try {
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
            } catch (Exception e) {
                IndustrialTech.LOGGER.error("Unable to register item: " + field.getName() + "! The following error was thrown:");
                IndustrialTech.LOGGER.catching(Level.ERROR, e);
            }
        }

        for (Field field : ITBlocks.class.getDeclaredFields()) {
            if (field.isAnnotationPresent(RegisterItemBlock.class)) try {
                RegisterItemBlock details = field.getAnnotation(RegisterItemBlock.class);
                Block block = (Block) field.get(null);
                if (block == null) {
                    block = (Block) field.getType().newInstance();
                    field.set(null, block);
                }

                ItemBlock item = null;
                if (!details.customItemBlock()) {
                    item = new ItemBlock(block);
                } else if (block instanceof ICustomItemBlock) {
                    ICustomItemBlock customItemBlock = (ICustomItemBlock) block;
                    item = customItemBlock.getCustomItemBlock();
                } else {
                    IndustrialTech.LOGGER.error("Tried to register custom item block but none was found! Please ensure the block is an instance of cjminecraft.industrialtech.utils.registries.ICustomItemBlock");
                    continue;
                }

                item.setRegistryName(new ResourceLocation(IndustrialTech.MODID, details.registryName()));
                if (!details.unlocalizedName().isEmpty())
                    item.setUnlocalizedName(details.unlocalizedName());
                else
                    item.setUnlocalizedName(details.registryName());
                event.getRegistry().register(item);
                registeredItems++;
            } catch (Exception e) {
                IndustrialTech.LOGGER.error("Unable to register item block: " + field.getName() + "! The following error was thrown:");
                IndustrialTech.LOGGER.catching(Level.ERROR, e);
            }
        }
        IndustrialTech.LOGGER.info("Successfully registered " + registeredItems + " items!");
    }

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event) {
        IndustrialTech.LOGGER.info("Searching for blocks to register");
        int registeredBlocks = 0;
        for (Field field : ITBlocks.class.getDeclaredFields()) {
            if (field.isAnnotationPresent(RegisterBlock.class)) try {
                RegisterBlock details = field.getAnnotation(RegisterBlock.class);
                Block block = (Block) field.get(null);
                if (block == null) {
                    block = (Block) field.getType().newInstance();
                    field.set(null, block);
                }
                block.setRegistryName(new ResourceLocation(IndustrialTech.MODID, details.registryName()));
                if (!details.unlocalizedName().isEmpty())
                    block.setUnlocalizedName(details.unlocalizedName());
                else
                    block.setUnlocalizedName(details.registryName());
                event.getRegistry().register(block);
                registeredBlocks++;
            } catch (Exception e) {
                IndustrialTech.LOGGER.error("Unable to register block: " + field.getName() + "! The following error was thrown:");
                IndustrialTech.LOGGER.catching(Level.ERROR, e);
            }
        }
        IndustrialTech.LOGGER.info("Successfully registered " + registeredBlocks + " blocks!");
    }

}
