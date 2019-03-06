package xyz.dreeks.weirdadditions.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class HasSporeStorage implements Capability.IStorage<IHasSpore> {

    @Nullable
    @Override
    public NBTBase writeNBT(Capability<IHasSpore> capability, IHasSpore instance, EnumFacing side) {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("spore", instance.getAmountLeft());
        nbt.setInteger("maxspore", instance.getMaxSpore());

        return nbt;
    }

    @Override
    public void readNBT(Capability<IHasSpore> capability, IHasSpore instance, EnumFacing side, NBTBase nbt) {
        NBTTagCompound tag = (NBTTagCompound)nbt;

        instance.setAmountLeft(tag.getInteger("spore"));
        instance.setMaxSpore(tag.getInteger("maxspore"));
    }

}
