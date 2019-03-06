package xyz.dreeks.weirdadditions.recipes;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemFishFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import xyz.dreeks.weirdadditions.WeirdAdditions;
import xyz.dreeks.weirdadditions.items.ItemSporeExtractor;

import javax.annotation.Nonnull;

class RecipeFishSpore extends ShapelessOreRecipe {
    // initialize a classic shapeless recipe
    public RecipeFishSpore(ResourceLocation group, NonNullList<Ingredient> input, ItemStack result) {
        super(group, input, result);
    }

    // give the remaining items list a special treatment to increase the spore extractor's damage
    @Override
    @Nonnull
    public NonNullList<ItemStack> getRemainingItems(@Nonnull InventoryCrafting inv) {
        // get the crafting table size
        int craftingBenchSize = inv.getSizeInventory();

        // create an empty crafting table item list
        NonNullList<ItemStack> list = NonNullList.withSize(craftingBenchSize, ItemStack.EMPTY);

        // let's not touch the input inventory object (maybe this is overkill? who knows)
        ItemStack stackOld;
        ItemStack stackNew;

        int i = 0;

        while (i < craftingBenchSize) {
            stackOld = inv.getStackInSlot(i);

            if ((stackOld.getItem() instanceof ItemSporeExtractor)
                    && (stackOld.getItemDamage() < WeirdAdditions.config.SPOREEXTRACTOR_MAX_DAMAGE)) {
                // create un updated version of the stack (one item by default)
                stackNew = new ItemStack(stackOld.getItem());

                // update item damage based on the old stack values
                stackNew.setItemDamage(stackOld.getItemDamage() + 1);

                // put the item at the right place in the list
                list.set(i, stackNew);

                // we don't need to check further as this is the only item supposed to stay in the crafting table
                break;
            }

            ++i;
        }

        return list;
    }

    @Override
    @Nonnull
    public ItemStack getCraftingResult(@Nonnull InventoryCrafting list) {
        ItemStack stackOut = super.getCraftingResult(list);
        int meta = 0;

        int craftingBenchSize = list.getSizeInventory();
        ItemStack stackIn;
        int i = 0;

        while (i < craftingBenchSize) {
            stackIn = list.getStackInSlot(i);

            if (stackIn.getItem() instanceof ItemFishFood) {
                meta = stackIn.getMetadata();
                break;
            }

            ++i;
        }

        // @TODO
        // we prevent non-default fish types from returning a spore,
        // because the SporeFish class does not support metadata yet.
        // when other fish crops are implemented, simply remove this
        // to enable automatic metadata transmission to the result.
        if (meta > 0) {
            return ItemStack.EMPTY;
        }

        return new ItemStack(stackOut.getItem(), stackOut.getCount(), meta);
    }
}

// mostly copied from ShapelessRecipes
public class RecipeSpore implements IRecipeFactory {
    @Nonnull
    private static NonNullList<Ingredient> deserializeIngredients(JsonArray array) {
        NonNullList<Ingredient> ingredientsList = NonNullList.<Ingredient>create();

        for (int i = 0; i < array.size(); ++i) {
            Ingredient ingredient = ShapedRecipes.deserializeIngredient(array.get(i));

            if (ingredient != Ingredient.EMPTY) {
                ingredientsList.add(ingredient);
            }
        }

        return ingredientsList;
    }

    @Override
    public IRecipe parse(JsonContext context, JsonObject json) {
        {
            // @TODO initialize the recipe book group (see forge docs)
            /*String group = JsonUtils.getString(json, "group", "");*/

            // check if the recipe actually targets this class
            String type = JsonUtils.getString(json, "type", "");

            // get the ingredients
            NonNullList<Ingredient> ingredients = deserializeIngredients(JsonUtils.getJsonArray(json, "ingredients"));

            // check the list
            if (ingredients.isEmpty()) {
                throw new JsonParseException("No ingredients for shapeless recipe");
            } else if (ingredients.size() > 9) {
                throw new JsonParseException("Too many ingredients for shapeless recipe");
            } else {
                // get the resulting stack
                ItemStack result = ShapedRecipes.deserializeItem(JsonUtils.getJsonObject(json, "result"), true);

                // process parsed info with the right recipe class
                if (type.equals("weirdadditions:spore")) {
                    return new RecipeFishSpore(new ResourceLocation(context.getModId()), ingredients, result);
                } else {
                    return null;
                }
            }
        }
    }
}
