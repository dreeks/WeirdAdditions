package xyz.dreeks.weirdadditions.items;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumPlantType;
import xyz.dreeks.weirdadditions.blocks.WABlocks;

public class ItemSporeCow extends ItemBaseSeeds{
    public ItemSporeCow() {
        super("spore_cow", WABlocks.cowCrops);
    }

    @Override
    public IBlockState getPlant(IBlockAccess world, BlockPos pos) {
        return WABlocks.cowCrops.getDefaultState();
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
        return EnumPlantType.Crop;
    }
}
