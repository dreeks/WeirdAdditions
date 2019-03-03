package xyz.dreeks.weirdadditions.events;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import xyz.dreeks.weirdadditions.items.WAItems;
import xyz.dreeks.weirdadditions.utils.WASounds;

import static xyz.dreeks.weirdadditions.utils.Constants.MOD_ID;


@Mod.EventBusSubscriber(modid = MOD_ID)
public class WAEventHandler {

    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event) {
    }

    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> registry = event.getRegistry();

        for (Item i : WAItems.items) {
            registry.register(i);
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void registerModels(ModelRegistryEvent mre) {
        for (Item i : WAItems.items) {
            ModelLoader.setCustomModelResourceLocation(i, 0, new ModelResourceLocation(i.getRegistryName(), "inventory"));
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void registerSounds(final RegistryEvent.Register<SoundEvent> sound) {
        final IForgeRegistry<SoundEvent> registry = sound.getRegistry();

        for (SoundEvent i : WASounds.sounds.values()) {
            registry.register(i);
        }
    }
}
