package xyz.dreeks.weirdadditions.events;

import net.minecraftforge.common.MinecraftForge;

public class WAEvents {
    public static void preInit() {
        MinecraftForge.EVENT_BUS.register(new CapabilitiesEvents());
    }
}
