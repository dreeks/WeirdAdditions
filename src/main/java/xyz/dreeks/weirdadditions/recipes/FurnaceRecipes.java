package xyz.dreeks.weirdadditions.recipes;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import xyz.dreeks.weirdadditions.items.WAItems;

public class FurnaceRecipes {

    /** This class will disappear as soon as Forge supports furnace JSON recipes **/
    public static void load() {
        GameRegistry.addSmelting(Items.ROTTEN_FLESH, new ItemStack(WAItems.slightlyLessRottenFlesh), .0f);
        GameRegistry.addSmelting(WAItems.slightlyLessRottenFlesh, new ItemStack(WAItems.evenLessRottenFlesh), .0f);
        GameRegistry.addSmelting(WAItems.evenLessRottenFlesh, new ItemStack(Items.COAL, 1, 1), .0f);
    }

}
