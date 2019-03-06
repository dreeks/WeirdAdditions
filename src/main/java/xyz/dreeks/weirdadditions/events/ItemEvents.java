package xyz.dreeks.weirdadditions.events;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import xyz.dreeks.weirdadditions.blocks.IHasItem;
import xyz.dreeks.weirdadditions.blocks.WABlocks;
import xyz.dreeks.weirdadditions.items.WAItems;
import xyz.dreeks.weirdadditions.utils.Constants;

@Mod.EventBusSubscriber(modid = Constants.MOD_ID)
public class ItemEvents {

    @SubscribeEvent
    public void onLootTablesLoaded(LootTableLoadEvent evt) {
        if (evt.getName().equals(LootTableList.CHESTS_SIMPLE_DUNGEON)) {
            final LootPool lp = evt.getTable().getPool("main");
            if (lp != null) {
                lp.addEntry(new LootEntryItem(WAItems.recordMii, 10, 0, new LootFunction[0], new LootCondition[0], "loottable:recordmii"));
                lp.addEntry(new LootEntryItem(WAItems.recordHomebrew, 10, 0, new LootFunction[0], new LootCondition[0], "loottable:recordhomebrew"));
                lp.addEntry(new LootEntryItem(WAItems.recordChiracSamba, 10, 0, new LootFunction[0], new LootCondition[0], "loottable:recordchirac"));
            }
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
}
