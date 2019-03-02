package xyz.dreeks.weirdadditions.blocks;

import net.minecraft.block.BlockCrops;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import xyz.dreeks.weirdadditions.WeirdAdditions;

import java.util.Random;

public class BlockBaseCrop extends BlockCrops {

    public static final PropertyInteger CROP_AGE = PropertyInteger.create("age", 0, 4);

    public BlockBaseCrop(String name) {
        super();
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
        this.setCreativeTab(WeirdAdditions.instance.creativeTab);
    }

    protected PropertyInteger getAgeProperty() {
        return CROP_AGE;
    }

    public int getMaxAge() {
        return 4;
    }

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        /*if (rand.nextInt(3) == 0) {
            this.checkAndDropBlock(worldIn, pos, state);
        } else {
            super.updateTick(worldIn, pos, state, rand);
        }*/
    }

    protected int getBonemealAgeIncrease(World worldIn) {
        return MathHelper.getInt(worldIn.rand, 1, 3);
    }

    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{CROP_AGE});
    }

}
