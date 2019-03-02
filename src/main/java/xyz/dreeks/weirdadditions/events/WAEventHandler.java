package xyz.dreeks.weirdadditions.events;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import xyz.dreeks.weirdadditions.items.WAItems;
import xyz.dreeks.weirdadditions.utils.Constants;


@Mod.EventBusSubscriber(modid = Constants.MOD_ID)
public class WAEventHandler {

    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll();
    }

    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> registry = event.getRegistry();

        for(Item i : WAItems.items){
            registry.register(i);
        }
    }

}
