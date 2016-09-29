package cjminecraft.industrialtech.handlers;

import cjminecraft.industrialtech.events.CraftingEvents;
import cjminecraft.industrialtech.events.FirstJoinEvent;
import cjminecraft.industrialtech.events.GeneratorEvents;
import cjminecraft.industrialtech.events.SoulStealerEvents;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EventHandler {
	
	public void registerEvents() {
		MinecraftForge.EVENT_BUS.register(new SoulStealerEvents());
		MinecraftForge.EVENT_BUS.register(new GeneratorEvents());
		MinecraftForge.EVENT_BUS.register(new FirstJoinEvent());
		MinecraftForge.EVENT_BUS.register(new CraftingEvents());
	}

}
