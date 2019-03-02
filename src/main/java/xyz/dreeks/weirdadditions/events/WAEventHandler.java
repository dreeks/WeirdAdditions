package xyz.dreeks.weirdadditions.events;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import xyz.dreeks.weirdadditions.blocks.BlockBase;
import xyz.dreeks.weirdadditions.blocks.IHasItem;
import xyz.dreeks.weirdadditions.blocks.WABlocks;
import xyz.dreeks.weirdadditions.items.WAItems;
import xyz.dreeks.weirdadditions.utils.Constants;


@Mod.EventBusSubscriber(modid = Constants.MOD_ID)
public class WAEventHandler {

    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event) {
        final IForgeRegistry<Block> registry = event.getRegistry();

        for (Block b : WABlocks.blocks) {
            registry.register(b);
        }
    }

    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> registry = event.getRegistry();

        for (Item i : WAItems.items) {
            registry.register(i);
        }

        for (Block b : WABlocks.blocks) {
            if (b instanceof IHasItem && ((IHasItem) b).hasItem()) {
                registry.register(new ItemBlock(b).setRegistryName(b.getRegistryName()));
            }
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void registerModels(ModelRegistryEvent mre) {
        for (Item i : WAItems.items) {
            ModelLoader.setCustomModelResourceLocation(i, 0, new ModelResourceLocation(i.getRegistryName(), "inventory"));
        }

        /*for (Block b : WABlocks.blocks) {
            ModelLoader.setCustomModelResourceLocation(b, 0, new ModelResourceLocation(b.getRegistryName(), "inventory"));
        }*/
    }

}
