package xyz.dreeks.weirdadditions.blocks;

import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import xyz.dreeks.weirdadditions.WeirdAdditions;

import java.util.Random;

public class BlockBaseCrop extends BlockCrops {
    public static final PropertyInteger CROP_AGE = PropertyInteger.create("age", 0, 4);
    public static final PropertyDirection FACING = BlockHorizontal.FACING;

    public BlockBaseCrop(String name) {
        super();
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
        this.setCreativeTab(WeirdAdditions.instance.creativeTab);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
    }

    //#region Crop stuff
    @Override
    protected PropertyInteger getAgeProperty() {
        return CROP_AGE;
    }

    @Override
    public int getMaxAge() {
        return 4;
    }

    @Override
    public void grow(World worldIn, BlockPos pos, IBlockState state)
    {
        EnumFacing backupBeforeGrow = state.getValue(FACING);
        super.grow(worldIn, pos, state);
        worldIn.setBlockState(pos, worldIn.getBlockState(pos).withProperty(FACING, backupBeforeGrow));
    }
 

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        EnumFacing backupBeforeGrow = state.getValue(FACING);
        if (rand.nextInt(3) == 0) {
            this.checkAndDropBlock(worldIn, pos, state);
        } else {
            super.updateTick(worldIn, pos, state, rand);
        }
        worldIn.setBlockState(pos, worldIn.getBlockState(pos).withProperty(FACING, backupBeforeGrow));
    }

    @Override
    public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state)
    {
        return (worldIn.getLight(pos) >= 8 || worldIn.canSeeSky(pos)) && this.canSustainBush(worldIn.getBlockState(pos.down()));
    }

    @Override
    public void getDrops(net.minecraft.util.NonNullList<ItemStack> drops, net.minecraft.world.IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        drops.add(new ItemStack(this.getSeed(), 1, 0));
    }

    @Override
    protected int getBonemealAgeIncrease(World worldIn) {
        return MathHelper.getInt(worldIn.rand, 1, 3);
    }
    //#endregion

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{CROP_AGE, FACING});
    }
}
