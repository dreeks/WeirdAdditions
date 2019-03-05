package xyz.dreeks.weirdadditions.proxy;

import net.minecraftforge.fml.relauncher.Side;
import xyz.dreeks.weirdadditions.network.ParticlePacket;
import xyz.dreeks.weirdadditions.network.WANetwork;
import xyz.dreeks.weirdadditions.utils.Constants;

public class Client extends Common {

    @Override
    public void registerNetwork() {
        WANetwork.INSTANCE.registerMessage(ParticlePacket.ParticleClientHandler.class, ParticlePacket.class, Constants.PACKET_PARTICLE, Side.CLIENT);
    }

    @Override
    public boolean isClient() {
        return true;
    }

}
