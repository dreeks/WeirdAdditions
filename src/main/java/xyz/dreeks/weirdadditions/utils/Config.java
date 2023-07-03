package xyz.dreeks.weirdadditions.utils;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class Config {

    public static void register() {
        Config.registerClientConfigs();
        Config.registerCommonConfigs();
        Config.registerServerConfigs();
    }

    private static void registerClientConfigs() {
        // No client config yet
    }

    private static void registerCommonConfigs() {
        // No common configs yet
    }

    private static void registerServerConfigs() {
        ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();

        // Adding the configs

        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, SERVER_BUILDER.build());
    }

}
