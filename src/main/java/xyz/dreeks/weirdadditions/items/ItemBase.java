package xyz.dreeks.weirdadditions.items;

import net.minecraft.item.Item;
import xyz.dreeks.weirdadditions.WeirdAdditions;

public class ItemBase extends Item {

    public ItemBase(String unlocalizedName) {
        this.setRegistryName(unlocalizedName);
        this.setUnlocalizedName(unlocalizedName);
        this.setCreativeTab(WeirdAdditions.instance.creativeTab);
    }


}
