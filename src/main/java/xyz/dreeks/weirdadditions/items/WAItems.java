package xyz.dreeks.weirdadditions.items;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.dreeks.weirdadditions.utils.Constants;

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
    public static ItemSporeFish sporeFish;
    public static ItemBase foodMixture;
    public static ItemMixedJuice mixedJuice;
    public static ItemHalfCoal halfCoal;
    public static ItemRotator rotator;
    public static ItemHedgeTrimmer hedgeTrimmer;

    public static void preInit(FMLPreInitializationEvent fpie) {
        items.add(slightlyLessRottenFlesh = new ItemSlightlyLessRottenFlesh());
        items.add(evenLessRottenFlesh = new ItemEvenLessRottenFlesh());
        items.add(recordHomebrew = new ItemRecordHomebrew());
        items.add(recordMii = new ItemRecordMii());
        items.add(recordChiracSamba = new ItemRecordChiracSamba());
        items.add(sporeExtractor = new ItemSporeExtractor());
        items.add(sporeCow = new ItemSporeCow());
        items.add(sporeFish = new ItemSporeFish());
        items.add(foodMixture = new ItemBase("food_mixture"));
        items.add(mixedJuice = new ItemMixedJuice());
        items.add(halfCoal = new ItemHalfCoal());
        items.add(hedgeTrimmer = new ItemHedgeTrimmer());

        if (Constants.COMPAT_IC2) {
            items.add(rotator = new ItemRotator());
        }
    }
}
