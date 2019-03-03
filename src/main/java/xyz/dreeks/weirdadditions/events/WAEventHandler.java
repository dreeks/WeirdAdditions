package xyz.dreeks.weirdadditions.events;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;
import net.minecraft.item.ItemBlock;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import xyz.dreeks.weirdadditions.blocks.IHasItem;
import xyz.dreeks.weirdadditions.blocks.WABlocks;
import xyz.dreeks.weirdadditions.items.WAItems;
import xyz.dreeks.weirdadditions.utils.WASounds;
import xyz.dreeks.weirdadditions.utils.Constants;


@Mod.EventBusSubscriber(modid = Constants.MOD_ID)
public class WAEventHandler {

    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event) {
        final IForgeRegistry<Block> registry = event.getRegistry();

        for (Block b : WABlocks.blocks) {
            registry.register(b);
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

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void registerModels(ModelRegistryEvent mre) {
        for (Item i : WAItems.items) {
            ModelLoader.setCustomModelResourceLocation(i, 0, new ModelResourceLocation(i.getRegistryName(), "inventory"));
        }

        for (Block b : WABlocks.blocks) {
            Item item = Item.getItemFromBlock(b);

            if (item == Items.AIR) {
                continue;
            }

            ModelResourceLocation model = new ModelResourceLocation(item.getRegistryName(), "inventory");
            ModelLoader.setCustomModelResourceLocation(item, 0, model);
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
}
