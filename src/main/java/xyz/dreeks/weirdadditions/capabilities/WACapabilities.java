package xyz.dreeks.weirdadditions.capabilities;

import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class WACapabilities {

    public static void preInit(FMLPreInitializationEvent fpie) {
        CapabilityManager.INSTANCE.register(IHasSpore.class, new HasSporeStorage(), new HasSporeFactory());
    }

}
