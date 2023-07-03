package xyz.dreeks.weirdadditions.capabilities;

import net.minecraft.nbt.CompoundTag;

public class SporeCapability {
    // Will be used later to replanish over time
    private int maxSpores;
    private int amtSpores;

    public SporeCapability(int amtSpores) {
        this.amtSpores = amtSpores;
        this.maxSpores = amtSpores;
    }

    public int getAmtSpores() {
        return this.amtSpores;
    }

    public int getMaxAmtSpores() {
        return this.maxSpores;
    }

    public boolean consumeSpore() {
        if (this.amtSpores > 0) {
            this.amtSpores--;

            return true;
        }

        return false;
    }

    public void copyFrom(SporeCapability source) {
        this.amtSpores = source.getAmtSpores();
        this.maxSpores = source.getMaxAmtSpores();
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putInt("amt_spores", this.amtSpores);
        nbt.putInt("max_amt_spores", this.maxSpores);
    }

    public void loadNBTData(CompoundTag nbt) {
        this.amtSpores = nbt.getInt("amt_spores");
        this.maxSpores = nbt.getInt("max_amt_spores");
    }
}
