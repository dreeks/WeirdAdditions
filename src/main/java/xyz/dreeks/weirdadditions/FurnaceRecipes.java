package xyz.dreeks.weirdadditions;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import xyz.dreeks.weirdadditions.items.WAItems;

public class FurnaceRecipes {

    /** This class will disappear as soon as Forge supports furnace JSON recipes **/
    public static void load() {
        GameRegistry.addSmelting(Item.getByNameOrId("rotten_flesh"), new ItemStack(WAItems.slightlyLessRottenFlesh), .0f);
    }

}
