package xyz.dreeks.weirdadditions.items;

import xyz.dreeks.weirdadditions.WeirdAdditions;

public class ItemEvenLessRottenFlesh extends ItemBaseFood {

    public ItemEvenLessRottenFlesh() {
        super("elrf", WeirdAdditions.config.ELRF_FEED_AMOUNT, WeirdAdditions.config.ELRF_FEED_SATURATION, false);
    }

}
