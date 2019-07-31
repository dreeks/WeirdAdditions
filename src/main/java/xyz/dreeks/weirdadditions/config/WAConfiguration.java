package xyz.dreeks.weirdadditions.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class WAConfiguration {

    private Configuration config;

    public int SPOREEXTRACTOR_MAX_DAMAGE, SPOREEXTRACTOR_LUCK;

    public int SLRF_FEED_AMOUNT, ELRF_FEED_AMOUNT;
    public float SLRF_FEED_SATURATION, ELRF_FEED_SATURATION;

    public int MIXED_JUICE_AMOUNT, MIXED_JUICE_QUANTITY;
    public float MIXED_JUICE_SATURATION;
    public boolean MIXED_JUICE_EDIBLE_WHEN_NOT_HUNGRY;

    public int MAX_SPORE_PER_COW;

    public WAConfiguration(FMLPreInitializationEvent fpie) {
        config = new Configuration(fpie.getSuggestedConfigurationFile());
        config.load();

        SPOREEXTRACTOR_MAX_DAMAGE = config.get("Items", "SPORE_EXTRACTOR_MAX_USE", "30").getInt();
        SPOREEXTRACTOR_LUCK       = config.get("Items", "SPORE_EXTRACTOR_LUCK", "3").getInt();

        MAX_SPORE_PER_COW = config.get("Spore", "MAX_SPORE_PER_COW", "3").getInt();

        SLRF_FEED_AMOUNT     = config.get("Food", "SLRF_FEED_AMOUNT", "2").getInt();
        SLRF_FEED_SATURATION = (float) config.get("Food", "SLRF_SATURATION_AMOUNT", "0.8").getDouble();

        ELRF_FEED_AMOUNT     = config.get("Food", "ELRF_FEED_AMOUNT", "3").getInt();
        ELRF_FEED_SATURATION = (float) config.get("Food", "ELRF_SATURATION_AMOUNT", "1").getDouble();

        MIXED_JUICE_AMOUNT                 = config.get("Food", "MIXED_JUICE_AMOUNT", "4").getInt();
        MIXED_JUICE_QUANTITY               = config.get("Food", "MIXED_JUICE_QUANTITY", "20").getInt();
        MIXED_JUICE_SATURATION             = (float) config.get("Food", "MIXED_JUICE_SATURATION", "1.2").getDouble();
        MIXED_JUICE_EDIBLE_WHEN_NOT_HUNGRY = config.get("Food", "MIXED_JUICE_EDIBLE_WHEN_NOT_HUNGRY", "false").getBoolean();


        if (config.hasChanged())
            config.save();
    }
}
