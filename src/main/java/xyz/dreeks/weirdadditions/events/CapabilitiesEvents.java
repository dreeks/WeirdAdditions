package xyz.dreeks.weirdadditions.events;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Cow;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.dreeks.weirdadditions.capabilities.SporeCapability;
import xyz.dreeks.weirdadditions.capabilities.SporeProvider;
import xyz.dreeks.weirdadditions.utils.Constants;

@Mod.EventBusSubscriber(modid=Constants.MOD_ID)
public class CapabilitiesEvents {

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(SporeCapability.class);
    }

    @SubscribeEvent
    public void onAttachCapability(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Cow) {
            if (!event.getObject().getCapability(SporeProvider.SPORE_CAPABILITY).isPresent()) {
                event.addCapability(new ResourceLocation(Constants.MOD_ID, "capability_spore_provider"), new SporeProvider());
            }
        }
    }

}
