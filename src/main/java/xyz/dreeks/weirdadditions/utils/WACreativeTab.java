package xyz.dreeks.weirdadditions.utils;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WACreativeTab extends CreativeTabs {

    public WACreativeTab() {
        super("wa.name");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack getTabIconItem() {
        return new ItemStack(Items.CARROT_ON_A_STICK);
    }

}
