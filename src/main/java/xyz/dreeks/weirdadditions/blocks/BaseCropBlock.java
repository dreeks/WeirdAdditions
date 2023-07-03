package xyz.dreeks.weirdadditions.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public abstract class BaseCropBlock extends CropBlock {
    public static final IntegerProperty CUSTOM_AGE = BlockStateProperties.AGE_5;
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public BaseCropBlock(Properties properties) {
        super(properties);
    }

    @Override
    public int getMaxAge() {
        return 5;
    }

    @Override
    public @NotNull IntegerProperty getAgeProperty() {
        return BaseCropBlock.CUSTOM_AGE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BaseCropBlock.CUSTOM_AGE);
        builder.add(BaseCropBlock.FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this
                .defaultBlockState()
                .setValue(BaseCropBlock.CUSTOM_AGE, 0)
                .setValue(BaseCropBlock.FACING, context.getHorizontalDirection().getOpposite());
    }

    public abstract Entity createMobInstance(Level l);

    public void popTheMob(Level w, BlockPos bp, BlockState bs) {
        if (w.isClientSide) {
            return;
        }

        if (bs.getValue(BaseCropBlock.CUSTOM_AGE) != this.getMaxAge()) {
            System.out.println("Not grown: " + bs.getValue(BaseCropBlock.CUSTOM_AGE) + " / " + this.getMaxAge());
            return;
        }

        double x = bp.getX() + .5d;
        double y = bp.getY() + 1.d;
        double z = bp.getZ() + .5d;

        // @TODO Glittering the mob (Particle packet)

        //ParticlePacket pp = new ParticlePacket(EnumParticleTypes.EXPLOSION_NORMAL, x, y, z).setQuantity(10).setSpacing(.1d);
        //NetworkRegistry.TargetPoint target = new NetworkRegistry.TargetPoint(w.provider.getDimension(), x, y, z, 20.d);
        //WANetwork.INSTANCE.sendToAllAround(pp, target);

        Entity mob = this.createMobInstance(w);
        mob.setPos(x, y, z);
        w.addFreshEntity(mob);

        w.removeBlock(bp, false);
    }

    @Override
    @Deprecated
    public void randomTick(BlockState bs, ServerLevel w, BlockPos bp, Random r) {
        super.tick(bs, w, bp, r);
        this.popTheMob(w, bp, bs);
    }

    @Override
    public @NotNull InteractionResult use(BlockState bs, Level w, BlockPos bp, Player p, InteractionHand h, BlockHitResult bhr) {
        // @TODO fix this
        if (p.getItemInHand(h) == null) {
            this.popTheMob(w, bp, bs);
            return InteractionResult.SUCCESS;
        } else {
            return super.use(bs, w, bp, p, h, bhr);
        }
    }
}
