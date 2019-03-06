package xyz.dreeks.weirdadditions.events;

import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import xyz.dreeks.weirdadditions.blocks.WABlocks;
import xyz.dreeks.weirdadditions.utils.Constants;


@Mod.EventBusSubscriber(modid = Constants.MOD_ID)
public class BlockEvents {

    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event) {
        final IForgeRegistry<Block> registry = event.getRegistry();

        for (Block b : WABlocks.blocks) {
            registry.register(b);
        }
    }

}
