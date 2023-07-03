package xyz.dreeks.weirdadditions.items;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.RecordItem;
import net.minecraftforge.registries.RegistryObject;
import xyz.dreeks.weirdadditions.setup.ModSetup;

public class CustomRecordItem extends RecordItem {
    public CustomRecordItem(RegistryObject<SoundEvent> obj) {
        super(9, obj, (new Item.Properties()).tab(ModSetup.ITEM_GROUP).stacksTo(1));
    }
}
