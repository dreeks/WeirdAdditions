package xyz.dreeks.weirdadditions.utils;

import net.minecraftforge.fml.common.Loader;

public class Constants {

    public static final String MOD_NAME = "WeirdAdditions";
    public static final String MOD_ID = "weirdadditions";
    public static final String MOD_VERS = "0.1";

    public static final String CLIENT_PROXY_LOCATION = "xyz.dreeks.weirdadditions.proxy.Client";
    public static final String SERVER_PROXY_LOCATION = "xyz.dreeks.weirdadditions.proxy.Server";

    public static final int PACKET_PARTICLE = 0;

    public static boolean COMPAT_IC2;


    public static void CheckLoadedMods() {
        COMPAT_IC2 = Loader.isModLoaded("ic2");
    }

}
