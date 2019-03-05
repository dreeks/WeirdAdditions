package xyz.dreeks.weirdadditions.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

import java.nio.charset.Charset;

public class ParticlePacket implements IMessage {

    private boolean messageValid;
    private String particleType;
    private double x, y, z;
    private int quantity;
    private double spacing;

    public ParticlePacket() {
        this.messageValid = false;
    }

    public ParticlePacket(EnumParticleTypes type, double x, double y, double z) {
        this.particleType = type.getParticleName();
        this.x = x;
        this.y = y;
        this.z = z;
        this.messageValid = true;
        this.quantity = 1;
    }

    public ParticlePacket setQuantity(int i) {
        this.quantity = i;
        return this;
    }

    public ParticlePacket setSpacing(double d) {
        this.spacing = d;
        return this;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        try {
            int particleNameLength = buf.readInt();
            this.particleType = buf.readCharSequence(particleNameLength, Charset.defaultCharset()).toString();
            this.x = buf.readDouble();
            this.y = buf.readDouble();
            this.z = buf.readDouble();
            this.quantity = buf.readInt();
            this.spacing = buf.readDouble();
        } catch (Exception e) {
            return;
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        if (!this.messageValid) return;

        buf.writeInt(this.particleType.length());
        buf.writeCharSequence(this.particleType, Charset.defaultCharset());
        buf.writeDouble(this.x);
        buf.writeDouble(this.y);
        buf.writeDouble(this.z);
        buf.writeInt(this.quantity);
        buf.writeDouble(this.spacing);
    }

    public static class ParticleClientHandler implements IMessageHandler<ParticlePacket, IMessage> {
        @Override
        public IMessage onMessage(ParticlePacket message, MessageContext ctx) {

            if (!message.messageValid && ctx.side != Side.CLIENT)
                return null;

            Minecraft mc = Minecraft.getMinecraft();
            final WorldClient wc = mc.world;

            mc.addScheduledTask(() -> {
                double start = -(message.spacing * message.quantity)/2;
                double end   = -start;

                for (double d = start; d < end; d += message.spacing) {
                    wc.spawnParticle(EnumParticleTypes.getByName(message.particleType), message.x + d, message.y + d, message.z + d, .0d, .0d, .0d);
                }
            });

            return null;
        }
    }

    public static class ParticleServerHandler implements IMessageHandler<ParticlePacket, IMessage> {
        @Override
        public IMessage onMessage(ParticlePacket message, MessageContext ctx) {
            return null;
        }
    }



}
