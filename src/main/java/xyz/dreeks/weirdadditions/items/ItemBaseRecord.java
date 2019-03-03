package xyz.dreeks.weirdadditions.items;

import xyz.dreeks.weirdadditions.WeirdAdditions;
import xyz.dreeks.weirdadditions.utils.WASounds;

import net.minecraft.item.ItemRecord;

public class ItemBaseRecord extends ItemRecord {
    public ItemBaseRecord(String unlocalizedName) {
        super(unlocalizedName, WASounds.sounds.get(unlocalizedName));
        this.setRegistryName(unlocalizedName);
        this.setUnlocalizedName(unlocalizedName);
        this.setCreativeTab(WeirdAdditions.instance.creativeTab);
    }
}
