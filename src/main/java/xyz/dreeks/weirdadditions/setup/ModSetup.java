package xyz.dreeks.weirdadditions.setup;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class ModSetup {
    public static final CreativeModeTab ITEM_GROUP = new CreativeModeTab("wa.name") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ItemRegistration.ITEM_SLIGHTLY_LESS_ROTTEN_FLESH.get());
        }
    };

    public static void init(final FMLCommonSetupEvent event) {

    }
}
