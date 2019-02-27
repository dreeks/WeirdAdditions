package xyz.dreeks.weirdadditions.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class WAConfiguration {

    private Configuration config;

    public WAConfiguration(FMLPreInitializationEvent fpie) {
        config = new Configuration(fpie.getSuggestedConfigurationFile());
        config.load();

        /**
         * Adding config here
         */

        if (config.hasChanged())
            config.save();
    }

}
