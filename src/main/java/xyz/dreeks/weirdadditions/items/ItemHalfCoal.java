package xyz.dreeks.weirdadditions.items;

import net.minecraft.item.ItemStack;

public class ItemHalfCoal extends ItemBase {

    public ItemHalfCoal() {
        super("half_coal");
    }

    @Override
    public int getItemBurnTime(ItemStack is){
        if (is.getItem() == WAItems.halfCoal)
            return 800;

        return -1;
    }

}