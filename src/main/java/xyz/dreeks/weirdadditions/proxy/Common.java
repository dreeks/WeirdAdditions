package xyz.dreeks.weirdadditions.proxy;

import net.minecraftforge.fml.common.registry.GameRegistry;
import xyz.dreeks.weirdadditions.blocks.WABlocks;
import xyz.dreeks.weirdadditions.tileentities.TEPartyRender;

public class Common implements IProxy {
    @Override
    public void registerTileEntities() {

    }

    @Override
    public void registerEntities() {

    }

    @Override
    public void registerRenders() {
        GameRegistry.registerTileEntity(TEPartyRender.class, WABlocks.brf.getRegistryName().toString());
    }

    @Override
    public void registerNetwork() {

    }

    @Override
    public boolean isClient() {
        return false;
    }
}
