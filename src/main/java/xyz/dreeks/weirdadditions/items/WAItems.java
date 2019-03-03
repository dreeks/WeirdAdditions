package xyz.dreeks.weirdadditions.items;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.ArrayList;

public class WAItems {

    public static ArrayList<Item> items = new ArrayList<>();

    public static ItemSlightlyLessRottenFlesh slightlyLessRottenFlesh;
    public static ItemEvenLessRottenFlesh evenLessRottenFlesh;
    public static ItemRecordHomebrew recordHomebrew;
    public static ItemRecordMii recordMii;
    public static ItemRecordChiracSamba recordChiracSamba;
    public static ItemSporeExtractor sporeExtractor;
    public static ItemSporeCow sporeCow;

    public static void preInit(FMLPreInitializationEvent fpie) {
        items.add(slightlyLessRottenFlesh = new ItemSlightlyLessRottenFlesh());
        items.add(evenLessRottenFlesh = new ItemEvenLessRottenFlesh());
        items.add(recordHomebrew = new ItemRecordHomebrew());
        items.add(recordMii = new ItemRecordMii());
        items.add(recordChiracSamba = new ItemRecordChiracSamba());
        items.add(sporeExtractor = new ItemSporeExtractor());
        items.add(sporeCow = new ItemSporeCow());
    }
}
