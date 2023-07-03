package xyz.dreeks.weirdadditions.capabilities;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class SporeProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<SporeCapability> SPORE_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {});

    private SporeCapability instance = null;
    private final LazyOptional<SporeCapability> optional = LazyOptional.of(this::getInstance);

    private SporeCapability getInstance() {
        if (this.instance == null) {
            Random r = new Random();
            int amtSpores = r.nextInt(2, 6); // Amt of spores capped per cow

            this.instance = new SporeCapability(amtSpores);
        }

        return this.instance;
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == SPORE_CAPABILITY) {
            return optional.cast();
        }

        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        this.getInstance().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.getInstance().loadNBTData(nbt);
    }
}
