package xyz.dreeks.weirdadditions.items;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import xyz.dreeks.weirdadditions.WeirdAdditions;
import xyz.dreeks.weirdadditions.capabilities.IHasSpore;

import java.util.Random;

import static xyz.dreeks.weirdadditions.capabilities.HasSporeProvider.SPORE_CAPA;

public class ItemSporeExtractor extends ItemBase {

    public ItemSporeExtractor() {
        super("spore_extractor");
        this.setMaxStackSize(1);
        this.setMaxDamage(WeirdAdditions.instance.config.SPOREEXTRACTOR_MAX_DAMAGE);
    }

    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer ep, EntityLivingBase target, EnumHand hand) {
        if (!ep.world.isRemote && target instanceof EntityCow) {
            if (target.hasCapability(SPORE_CAPA, EnumFacing.DOWN)) {
                IHasSpore capa = target.getCapability(SPORE_CAPA, EnumFacing.DOWN);
                int amt = capa.getAmountLeft();

                if (amt > 0) {
                    // @TODO find a better random
                    Random rand = new Random();
                    int i = rand.nextInt(WeirdAdditions.config.SPOREEXTRACTOR_LUCK);

                    if (i == 0) {
                        EntityItem ei = new EntityItem(target.world, target.posX, target.posY, target.posZ, new ItemStack(WAItems.sporeCow));
                        target.world.spawnEntity(ei);
                        capa.setAmountLeft(amt - 1);
                    }

                    if (stack.getItemDamage() < WeirdAdditions.config.SPOREEXTRACTOR_MAX_DAMAGE) {
                        stack.setItemDamage(stack.getItemDamage() + 1);
                    } else {
                        stack.setCount(0);
                    }

                    return true;
                }
            }
        }
        return false;
    }

}
