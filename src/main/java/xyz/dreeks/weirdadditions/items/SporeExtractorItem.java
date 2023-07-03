package xyz.dreeks.weirdadditions.items;

import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import xyz.dreeks.weirdadditions.capabilities.SporeProvider;
import xyz.dreeks.weirdadditions.setup.ModSetup;

public class SporeExtractorItem extends Item {

    public SporeExtractorItem(){
        super(
                (new Item.Properties())
                        .tab(ModSetup.ITEM_GROUP)
                        .stacksTo(1)
                        .durability(30)
        );
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack is, Player ep, LivingEntity target, InteractionHand hand) {
        if (!ep.level.isClientSide && target instanceof Cow) {
            var cap = target.getCapability(SporeProvider.SPORE_CAPABILITY, Direction.DOWN);

            if (cap.isPresent()) {
                var spores = cap.resolve().get();

                if (spores.getAmtSpores() > 0) {
                    System.out.println("Dropping spore");
                    spores.consumeSpore();

                    if (is.getDamageValue() < is.getMaxDamage()) {
                        is.setDamageValue(is.getDamageValue() +1);
                    } else {
                        is.setCount(0);
                    }
                }

                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.FAIL;
    }
}
