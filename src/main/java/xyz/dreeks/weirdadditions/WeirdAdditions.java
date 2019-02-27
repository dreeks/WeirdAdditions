package xyz.dreeks.weirdadditions;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = WeirdAdditions.MODID, name = WeirdAdditions.NAME, version = WeirdAdditions.VERSION)
public class WeirdAdditions
{
    public static final String MODID = "weirdadditions";
    public static final String NAME = "WeirdAdditions";
    public static final String VERSION = "0.1";

    private static Logger LOGGER;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        WeirdAdditions.LOGGER = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // some example code
        WeirdAdditions.LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }
}
