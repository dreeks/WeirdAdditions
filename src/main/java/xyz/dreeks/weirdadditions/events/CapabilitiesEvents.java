package xyz.dreeks.weirdadditions.events;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xyz.dreeks.weirdadditions.capabilities.HasSporeProvider;
import xyz.dreeks.weirdadditions.utils.Constants;

@Mod.EventBusSubscriber(modid = Constants.MOD_ID)
public class CapabilitiesEvents {

    @SubscribeEvent
    public void attachCapabilities(AttachCapabilitiesEvent<Entity> event) {

        if (event.getObject() instanceof EntityCow)
            event.addCapability(new ResourceLocation(Constants.MOD_ID, "capability_spore_provider"), new HasSporeProvider());
    }
}
