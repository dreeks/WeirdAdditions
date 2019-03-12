package xyz.dreeks.weirdadditions.proxy;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import xyz.dreeks.weirdadditions.network.ParticlePacket;
import xyz.dreeks.weirdadditions.network.WANetwork;
import xyz.dreeks.weirdadditions.tileentities.TEPartyRender;
import xyz.dreeks.weirdadditions.tileentities.TESRPartyRender;
import xyz.dreeks.weirdadditions.utils.Constants;

public class Client extends Common {

    @Override
    public void registerNetwork() {
        WANetwork.INSTANCE.registerMessage(ParticlePacket.ParticleClientHandler.class, ParticlePacket.class, Constants.PACKET_PARTICLE, Side.CLIENT);

        ClientRegistry.bindTileEntitySpecialRenderer(TEPartyRender.class, new TESRPartyRender());
    }

    @Override
    public boolean isClient() {
        return true;
    }

}
