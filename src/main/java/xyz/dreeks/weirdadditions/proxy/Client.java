package xyz.dreeks.weirdadditions.proxy;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import xyz.dreeks.weirdadditions.items.WAItems;

public class Client extends Common {

    @Override
    public boolean isClient() {
        return true;
    }

    @Override
    public void registerRenders() {
        ModelLoader.setCustomModelResourceLocation(WAItems.slightlyLessRottenFlesh, 0, new ModelResourceLocation(WAItems.slightlyLessRottenFlesh.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(WAItems.sporeExtractor, 0, new ModelResourceLocation(WAItems.sporeExtractor.getRegistryName(), "inventory"));
        System.out.println(">>> " + WAItems.slightlyLessRottenFlesh.getRegistryName());
    }
}
