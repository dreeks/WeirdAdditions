package xyz.dreeks.weirdadditions.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import xyz.dreeks.weirdadditions.WeirdAdditions;
import xyz.dreeks.weirdadditions.items.WAItems;
import xyz.dreeks.weirdadditions.network.ParticlePacket;
import xyz.dreeks.weirdadditions.network.WANetwork;

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
        this.popTheCow(worldIn, pos, state);
    }

    public void popTheCow(World w, BlockPos pos, IBlockState ibs) {
         if (!w.isRemote) {
            if (ibs.getValue(BlockBaseCrop.CROP_AGE) == this.getMaxAge()) {

                double x = pos.getX() + .5d;
                double y = pos.getY() + 1.d;
                double z = pos.getZ() + .5d;

                // Glittering the cow
                ParticlePacket pp = new ParticlePacket(EnumParticleTypes.EXPLOSION_NORMAL, x, y, z).setQuantity(10).setSpacing(.1d);
                NetworkRegistry.TargetPoint target = new NetworkRegistry.TargetPoint(w.provider.getDimension(), x, y, z, 20.d);
                WANetwork.INSTANCE.sendToAllAround(pp, target);

                // Spawning the cow
                EntityCow ec = new EntityCow(w);
                ec.setPosition(x, y, z);
                w.spawnEntity(ec);

                // Resetting the block
                w.setBlockToAir(pos);

            }
        }
    }

    // While in debug, this allows the cow to pop when you rightclick on it, not waiting for the tick to tick
    /*
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        this.popTheCow(worldIn, pos, state);
        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }*/

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return CROP_AABB[state.getValue(this.getAgeProperty()).intValue()];
    }

}
