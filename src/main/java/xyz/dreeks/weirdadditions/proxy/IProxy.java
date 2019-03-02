package xyz.dreeks.weirdadditions.proxy;

public interface IProxy {

    void registerTileEntities();
    void registerEntities();
    void registerRenders();
    boolean isClient();
}
