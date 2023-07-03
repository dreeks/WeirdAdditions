package xyz.dreeks.weirdadditions.setup;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import xyz.dreeks.weirdadditions.items.CustomRecordItem;
import xyz.dreeks.weirdadditions.items.HalfCoalItem;
import xyz.dreeks.weirdadditions.items.SporeExtractorItem;
import xyz.dreeks.weirdadditions.utils.Constants;
import xyz.dreeks.weirdadditions.utils.Foods;

public class ItemRegistration {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Constants.MOD_ID);
    public static final RegistryObject<Item> ITEM_SLIGHTLY_LESS_ROTTEN_FLESH = ITEMS.register("slrf", () -> new Item((new Item.Properties()).tab(ModSetup.ITEM_GROUP).food(Foods.SLRF)));
    public static final RegistryObject<Item> ITEM_EVEN_LESS_ROTTEN_FLESH = ITEMS.register("elrf", () -> new Item((new Item.Properties()).tab(ModSetup.ITEM_GROUP).food(Foods.ELRF)));
    public static final RegistryObject<Item> ITEM_FOOD_MIXTURE = ITEMS.register("food_mixture", () -> new Item((new Item.Properties()).tab(ModSetup.ITEM_GROUP)));

    public static final RegistryObject<Item> ITEM_HALF_COAL = ITEMS.register("half_coal", HalfCoalItem::new);

    public static final RegistryObject<Item> ITEM_RECORD_HOMEBREW = ITEMS.register("recordhomebrew", () -> new CustomRecordItem(SoundRegistration.SOUND_HOMEBREW));
    public static final RegistryObject<Item> ITEM_RECORD_MII = ITEMS.register("recordmii", () -> new CustomRecordItem(SoundRegistration.SOUND_MII));
    public static final RegistryObject<Item> ITEM_RECORD_CHIRACSAMBA = ITEMS.register("recordchiracsamba", () -> new CustomRecordItem(SoundRegistration.SOUND_CHIRACSAMBA));

    public static final RegistryObject<Item> ITEM_SPORE_EXTRACTOR = ITEMS.register("spore_extractor", SporeExtractorItem::new);
    public static final RegistryObject<Item> ITEM_SPORE_COW = ITEMS.register("spore_cow", () -> new ItemNameBlockItem(
            BlockRegistration.CROPS_COW.get(),
            new Item.Properties().tab(ModSetup.ITEM_GROUP)
    ));
    public static final RegistryObject<Item> ITEM_SPORE_FISH = ITEMS.register("spore_fish", () -> new ItemNameBlockItem(
            BlockRegistration.CROPS_FISH.get(),
            new Item.Properties().tab(ModSetup.ITEM_GROUP)
    ));

    public static void init() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static <B extends Block> RegistryObject<Item> fromBlock(RegistryObject<B> block) {
        Item.Properties ITEM_PROPERTIES = (new Item.Properties()).tab(ModSetup.ITEM_GROUP);
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), ITEM_PROPERTIES));
    }
}
