package xyz.dreeks.weirdadditions.items;

import xyz.dreeks.weirdadditions.WeirdAdditions;

public class ItemSlightlyLessRottenFlesh extends ItemBaseFood {

    public ItemSlightlyLessRottenFlesh() {
        super("slrf", WeirdAdditions.config.SLRF_FEED_AMOUNT, WeirdAdditions.config.SLRF_FEED_SATURATION, false);
    }

}
