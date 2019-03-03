package xyz.dreeks.weirdadditions.items;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.dreeks.weirdadditions.blocks.WABlocks;

import java.util.ArrayList;

public class WAItems {

    public static ArrayList<Item> items = new ArrayList<>();


    public static ItemBaseSeeds sporeCow;
    public static ItemBaseSeeds sporeFish;
    public static ItemSporeExtractor sporeExtractor;
    public static ItemSlightlyLessRottenFlesh slightlyLessRottenFlesh;
    public static ItemEvenLessRottenFlesh evenLessRottenFlesh;
    public static ItemRecordHomebrew recordHomebrew;
    public static ItemRecordMii recordMii;
    public static ItemRecordChiracSamba recordChiracSamba;

    public static void preInit(FMLPreInitializationEvent fpie) {
        /**
         * Adding items here
         */
        items.add(sporeExtractor = new ItemSporeExtractor());
        items.add(slightlyLessRottenFlesh = new ItemSlightlyLessRottenFlesh());
        items.add(evenLessRottenFlesh = new ItemEvenLessRottenFlesh());
        items.add(sporeCow = new ItemBaseSeeds("spore_cow", WABlocks.cowCrops, Blocks.FARMLAND));
        items.add(sporeFish = new ItemBaseSeeds("spore_fish", WABlocks.cowCrops, Blocks.FARMLAND));
        items.add(recordHomebrew = new ItemRecordHomebrew());
        items.add(recordMii = new ItemRecordMii());
        items.add(recordChiracSamba = new ItemRecordChiracSamba());
    }

}
