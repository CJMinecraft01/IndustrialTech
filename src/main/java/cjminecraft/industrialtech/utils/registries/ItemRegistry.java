package cjminecraft.industrialtech.utils.registries;

import cjminecraft.industrialtech.IndustrialTech;
import cjminecraft.industrialtech.init.ITItems;
import cjminecraft.industrialtech.utils.registries.annotations.RegisterItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.lang.reflect.Field;

@Mod.EventBusSubscriber
public class ItemRegistry {

    @SubscribeEvent
    public static void onRegisterEvent(RegistryEvent.Register<Item> event) {
        IndustrialTech.LOGGER.info("Searching for items to register");
        int registeredItems = 0;
        try {
            for (Field field : ITItems.class.getDeclaredFields()) {
                if (field.isAnnotationPresent(RegisterItem.class) && field.getType().isAssignableFrom(Item.class)) {
                    RegisterItem details = field.getAnnotation(RegisterItem.class);
                    Item item = (Item) field.get(null);
                    item.setRegistryName(new ResourceLocation(IndustrialTech.MODID, details.registryName()));
                    if(!details.unlocalizedName().isEmpty())
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
