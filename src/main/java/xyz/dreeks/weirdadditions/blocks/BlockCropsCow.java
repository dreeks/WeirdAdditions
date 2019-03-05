package xyz.dreeks.weirdadditions.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.item.Item;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import xyz.dreeks.weirdadditions.WeirdAdditions;
import xyz.dreeks.weirdadditions.items.WAItems;

import java.util.Random;

public class BlockCropsCow extends BlockBaseCrop {
    private static final AxisAlignedBB[] CROP_AABB = new AxisAlignedBB[]{
            new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D),
            new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.85D, 1.0D),
            new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.35D, 1.0D),
            new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.35D, 1.0D),
            new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.35D, 1.0D)
    };

    public BlockCropsCow() {
        super("crops_cow");
    }

    protected Item getSeed() {
        return WAItems.sporeCow;
    }

    protected Item getCrop() {
        return WAItems.sporeCow;
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        super.updateTick(worldIn, pos, state, rand);
        if (state.getValue(BlockBaseCrop.CROP_AGE) == this.getMaxAge()) {
            EntityCow ec = new EntityCow(worldIn);
            ec.setPosition(pos.getX(), pos.getY() + 2, pos.getZ());
            worldIn.spawnEntity(ec);
            worldIn.setBlockState(pos, WABlocks.cowCrops.getDefaultState());

        }
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return CROP_AABB[state.getValue(this.getAgeProperty()).intValue()];
    }

}
