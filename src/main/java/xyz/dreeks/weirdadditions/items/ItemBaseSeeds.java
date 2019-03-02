package xyz.dreeks.weirdadditions.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemSeeds;
import xyz.dreeks.weirdadditions.WeirdAdditions;

public class ItemBaseSeeds extends ItemSeeds {

    public ItemBaseSeeds(String unlocalizedName, Block crops, Block soil) {
        super(crops, soil);
        this.setRegistryName(unlocalizedName);
        this.setUnlocalizedName(unlocalizedName);
        this.setCreativeTab(WeirdAdditions.instance.creativeTab);
    }

}
