package xyz.dreeks.weirdadditions.setup;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import xyz.dreeks.weirdadditions.utils.Constants;

public class SoundRegistration {
    private static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Constants.MOD_ID);

    public static final RegistryObject<SoundEvent> SOUND_HOMEBREW = SOUNDS.register("recordhomebrew", () -> new SoundEvent(new ResourceLocation(Constants.MOD_ID, "recordhomebrew")));
    public static final RegistryObject<SoundEvent> SOUND_MII = SOUNDS.register("recordmii", () -> new SoundEvent(new ResourceLocation(Constants.MOD_ID, "recordmii")));
    public static final RegistryObject<SoundEvent> SOUND_CHIRACSAMBA = SOUNDS.register("recordchiracsamba", () -> new SoundEvent(new ResourceLocation(Constants.MOD_ID, "recordchiracsamba")));

    public static void init() {
        SOUNDS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
