package xyz.dreeks.weirdadditions.recipes;

import com.google.gson.JsonObject;
import net.minecraft.block.Block;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class RecipeSpore implements IRecipeFactory {

    @Override
    public IRecipe parse(JsonContext context, JsonObject json) {
        return null;
    }

}

class RecipeFishSpore extends ShapelessOreRecipe {

    public RecipeFishSpore(ResourceLocation group, Block result, Object... recipe) {
        super(group, result, recipe);
    }

}
