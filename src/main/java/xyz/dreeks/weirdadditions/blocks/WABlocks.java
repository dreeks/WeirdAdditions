package xyz.dreeks.weirdadditions.blocks;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.ArrayList;

public class WABlocks {
    public static ArrayList<Block> blocks = new ArrayList<>();

    public static BlockCropsCow cowCrops;

    public static void preInit(FMLPreInitializationEvent fpie) {
        /**
         * Adding blocks here
         */

        blocks.add(cowCrops = new BlockCropsCow());

    }

}
