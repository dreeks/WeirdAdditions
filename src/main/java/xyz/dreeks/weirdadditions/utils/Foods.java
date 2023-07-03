package xyz.dreeks.weirdadditions.utils;

import net.minecraft.world.food.FoodProperties;

public class Foods {
    public static final FoodProperties SLRF = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.8f).build();
    public static final FoodProperties ELRF = (new FoodProperties.Builder()).nutrition(3).saturationMod(1f).build();
}
