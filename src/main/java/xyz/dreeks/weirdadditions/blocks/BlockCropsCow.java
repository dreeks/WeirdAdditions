package xyz.dreeks.weirdadditions.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import xyz.dreeks.weirdadditions.items.WAItems;

public class BlockCropsCow extends BlockBaseCrop {

    private static final AxisAlignedBB[] CROP_AABB = new AxisAlignedBB[]{
            new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D),
            new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D),
            new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.35D, 1.0D),
            new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.40D, 1.0D),
            new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D)
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
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return CROP_AABB[state.getValue(this.getAgeProperty()).intValue()];
    }

}
