package xyz.dreeks.weirdadditions.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import xyz.dreeks.weirdadditions.items.WAItems;
import xyz.dreeks.weirdadditions.network.ParticlePacket;
import xyz.dreeks.weirdadditions.network.WANetwork;

import java.util.Random;

public class BlockCropsFish extends BlockBaseCrop {
    private static final AxisAlignedBB[] CROP_AABB = new AxisAlignedBB[]{
            new AxisAlignedBB(0.0d, 0.0d, 0.0d, 1.0d, 0.125d, 1.0d),
            new AxisAlignedBB(0.4d, 0.0d, 0.3d, .6d, 1d, .7d),
            new AxisAlignedBB(0.4d, 0.0d, 0.3d, .6d, 1d, .7d),
            new AxisAlignedBB(0.4d, 0.0d, 0.3d, .6d, 1d, .7d),
            new AxisAlignedBB(0.4d, 0.0d, 0.3d, .6d, 1d, .7d)
    };

    public BlockCropsFish() {
        super("crops_fish");
    }

    protected Item getSeed() {
        return WAItems.sporeFish;
    }

    protected Item getCrop() {
        return WAItems.sporeFish;
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        super.updateTick(worldIn, pos, state, rand);
        this.popTheFish(worldIn, pos, state);
    }

    public void popTheFish(World w, BlockPos pos, IBlockState ibs) {
         if (!w.isRemote) {
            if (ibs.getValue(BlockBaseCrop.CROP_AGE) == this.getMaxAge()) {

                double x = pos.getX() + .5d;
                double y = pos.getY() + 1.d;
                double z = pos.getZ() + .5d;

                // Glittering the fish
                ParticlePacket pp = new ParticlePacket(EnumParticleTypes.EXPLOSION_NORMAL, x, y, z).setQuantity(10).setSpacing(.1d);
                NetworkRegistry.TargetPoint target = new NetworkRegistry.TargetPoint(w.provider.getDimension(), x, y, z, 20.d);
                WANetwork.INSTANCE.sendToAllAround(pp, target);

                // Spawning the fish
                EntityItem ei = new EntityItem(w);
                ei.setItem(new ItemStack(Items.FISH, 1));
                ei.setPosition(x, y, z);
                w.spawnEntity(ei);

                // Removing the block
                w.setBlockToAir(pos);

            }
        }
    }



    // While in debug, this allows the cow to pop when you rightclick on it, not waiting for the tick to tick
    /*
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        this.popTheFish(worldIn, pos, state);
        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }*/

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return CROP_AABB[state.getValue(this.getAgeProperty()).intValue()];
    }

}
