package xyz.dreeks.weirdadditions.items;

import xyz.dreeks.weirdadditions.WeirdAdditions;
import net.minecraft.item.ItemRecord;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

import static xyz.dreeks.weirdadditions.utils.Constants.MOD_ID;

public class ItemBaseRecord extends ItemRecord {
    public ItemBaseRecord(String unlocalizedName) {
        super(unlocalizedName, new SoundEvent(new ResourceLocation(MOD_ID, unlocalizedName)));
        this.setRegistryName(unlocalizedName);
        this.setUnlocalizedName(unlocalizedName);
        this.setCreativeTab(WeirdAdditions.instance.creativeTab);
    }
}
