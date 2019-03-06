package xyz.dreeks.weirdadditions.events;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class WAEvents {

    public static void preInit(FMLPreInitializationEvent fpie) {
        MinecraftForge.EVENT_BUS.register(new BlockEvents());
        MinecraftForge.EVENT_BUS.register(new CapabilitiesEvents());
        MinecraftForge.EVENT_BUS.register(new ItemEvents());
        MinecraftForge.EVENT_BUS.register(new EntityEvents());
        MinecraftForge.EVENT_BUS.register(new ClientsideEvents());
    }

}
