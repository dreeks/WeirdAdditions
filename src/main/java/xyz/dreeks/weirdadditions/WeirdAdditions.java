package xyz.dreeks.weirdadditions;

import com.mojang.logging.LogUtils;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import xyz.dreeks.weirdadditions.events.CapabilitiesEvents;
import xyz.dreeks.weirdadditions.events.WAEvents;
import xyz.dreeks.weirdadditions.setup.*;
import xyz.dreeks.weirdadditions.utils.Config;
import xyz.dreeks.weirdadditions.utils.Constants;

@Mod(Constants.MOD_ID)
public class WeirdAdditions
{
    private static final Logger LOGGER = LogUtils.getLogger();

    public WeirdAdditions() {
        // Do not change the order of the following lines
        SoundRegistration.init();
        BlockRegistration.init();
        ItemRegistration.init();
        Config.register();

        IEventBus modbus = FMLJavaModLoadingContext.get().getModEventBus();
        modbus.addListener(ModSetup::init);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> modbus.addListener(ClientSetup::init));

        WAEvents.preInit();
    }
}
