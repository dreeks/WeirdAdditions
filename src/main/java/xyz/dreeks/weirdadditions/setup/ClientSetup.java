package xyz.dreeks.weirdadditions.setup;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import xyz.dreeks.weirdadditions.utils.Constants;

@Mod.EventBusSubscriber(modid= Constants.MOD_ID, value= Dist.CLIENT, bus=Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {
    public static void init(final FMLClientSetupEvent event) {

    }
}
