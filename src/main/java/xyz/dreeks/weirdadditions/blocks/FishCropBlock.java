package xyz.dreeks.weirdadditions.blocks;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Cod;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import xyz.dreeks.weirdadditions.setup.ItemRegistration;

public class FishCropBlock extends BaseCropBlock {
    public FishCropBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected @NotNull ItemLike getBaseSeedId() {
        return ItemRegistration.ITEM_SPORE_FISH.get();
    }

    @Override
    public Entity createMobInstance(Level l) {
        return new Cod(EntityType.COD, l);
    }
}
