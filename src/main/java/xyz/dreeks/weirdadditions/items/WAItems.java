package xyz.dreeks.weirdadditions.items;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.ArrayList;

public class WAItems {

    public static ArrayList<Item> items = new ArrayList<>();

    public static ItemSporeExtractor sporeExtractor;
    public static ItemSlightlyLessRottenFlesh slightlyLessRottenFlesh;
    public static ItemEvenLessRottenFlesh evenLessRottenFlesh;

    public static void preInit(FMLPreInitializationEvent fpie) {
        /**
         * Adding items here
         */
        items.add(sporeExtractor = new ItemSporeExtractor());
        items.add(slightlyLessRottenFlesh = new ItemSlightlyLessRottenFlesh());
        items.add(evenLessRottenFlesh = new ItemEvenLessRottenFlesh());

    }

}
