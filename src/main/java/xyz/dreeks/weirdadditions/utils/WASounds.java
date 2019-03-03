package xyz.dreeks.weirdadditions.utils;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.Hashtable;

import static xyz.dreeks.weirdadditions.WeirdAdditions.proxy;
import static xyz.dreeks.weirdadditions.utils.Constants.MOD_ID;

public class WASounds {
    public static Hashtable<String, SoundEvent> sounds = new Hashtable<>();

    private static void addSoundEvent(String string){
        ResourceLocation resource = new ResourceLocation(MOD_ID, "record" + string);
        SoundEvent sound = new SoundEvent(resource);
        sound.setRegistryName(resource);
        sounds.put(sound.getSoundName().getResourcePath(), sound);
    }

    public static void preInit(FMLPreInitializationEvent fpie) {
        if (proxy.isClient()){
            addSoundEvent("homebrew");
            addSoundEvent("mii");
            addSoundEvent("chiracsamba");
        }
    }
}
