package xyz.dreeks.weirdadditions;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;
import xyz.dreeks.weirdadditions.blocks.WABlocks;
import xyz.dreeks.weirdadditions.config.WAConfiguration;
import xyz.dreeks.weirdadditions.events.WAEventHandler;
import xyz.dreeks.weirdadditions.items.WAItems;
import xyz.dreeks.weirdadditions.proxy.IProxy;
import xyz.dreeks.weirdadditions.utils.Constants;
import xyz.dreeks.weirdadditions.utils.WACreativeTab;
import xyz.dreeks.weirdadditions.utils.WASounds;

@Mod(modid = Constants.MOD_ID, name = Constants.MOD_NAME, version = Constants.MOD_VERS)
public class WeirdAdditions {

    public static WAConfiguration config;

    @Mod.Instance
    public static WeirdAdditions instance;

    @SidedProxy(serverSide = Constants.SERVER_PROXY_LOCATION, clientSide = Constants.CLIENT_PROXY_LOCATION)
    public static IProxy proxy;

    private static Logger LOGGER;

    public WACreativeTab creativeTab;


    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        WeirdAdditions.LOGGER = event.getModLog();

        this.creativeTab = new WACreativeTab();

        WeirdAdditions.config = new WAConfiguration(event);

        WASounds.preInit(event);
        WAItems.preInit(event);
        WABlocks.preInit(event);

        MinecraftForge.EVENT_BUS.register(new WAEventHandler());
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        FurnaceRecipes.load();

    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent evt) {

    }
}
