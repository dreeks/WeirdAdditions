package xyz.dreeks.weirdadditions.items;

import net.minecraft.item.ItemFood;
import xyz.dreeks.weirdadditions.WeirdAdditions;

public class ItemBaseFood extends ItemFood {

    public ItemBaseFood(String unlocalizedName, int amount, float saturation, boolean isWolfFood) {
        super(amount, saturation, isWolfFood);
        this.setRegistryName(unlocalizedName);
        this.setUnlocalizedName(unlocalizedName);
        this.setCreativeTab(WeirdAdditions.instance.creativeTab);
    }

}
