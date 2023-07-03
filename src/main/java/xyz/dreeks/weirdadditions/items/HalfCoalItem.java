package xyz.dreeks.weirdadditions.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.Nullable;
import xyz.dreeks.weirdadditions.setup.ModSetup;

public class HalfCoalItem extends Item {
    public HalfCoalItem() {
        super((new Item.Properties()).tab(ModSetup.ITEM_GROUP));
    }

    @Override
    public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
        return 800;
    }
}
