package xyz.dreeks.weirdadditions.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class HasSporeProvider implements ICapabilitySerializable<NBTBase> {

    @CapabilityInject(IHasSpore.class)
    public static final Capability<IHasSpore> SPORE_CAPA = null;

    private IHasSpore instance = SPORE_CAPA.getDefaultInstance();

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == SPORE_CAPA;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return this.hasCapability(capability, facing) ? SPORE_CAPA.cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT() {
        return SPORE_CAPA.getStorage().writeNBT(SPORE_CAPA, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        SPORE_CAPA.getStorage().readNBT(SPORE_CAPA, this.instance, null, nbt);
    }
}
