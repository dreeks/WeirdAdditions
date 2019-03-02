package xyz.dreeks.weirdadditions.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class WAConfiguration {

    private Configuration config;

    public int SPOREEXTRACTOR_MAX_DAMAGE;

    public int SLRF_FEED_AMOUNT, ELRF_FEED_AMOUNT;
    public float SLRF_FEED_SATURATION, ELRF_FEED_SATURATION;

    public WAConfiguration(FMLPreInitializationEvent fpie) {
        config = new Configuration(fpie.getSuggestedConfigurationFile());
        config.load();

        /**
         * Adding config here
         */
        SPOREEXTRACTOR_MAX_DAMAGE = config.get("Items", "SPORE_EXTRACTOR_MAX_DAMAGE", "255").getInt();

        SLRF_FEED_AMOUNT = config.get("Food", "SLRF_FEED_AMOUNT", "2").getInt();
        SLRF_FEED_SATURATION = (float) config.get("Food", "SLRF_SATURATION_AMOUNT", "0.8").getDouble();

        ELRF_FEED_AMOUNT = config.get("Food", "ELRF_FEED_AMOUNT", "3").getInt();
        ELRF_FEED_SATURATION = (float) config.get("Food", "ELRF_SATURATION_AMOUNT", "1").getDouble();

        if (config.hasChanged())
            config.save();
    }

}
