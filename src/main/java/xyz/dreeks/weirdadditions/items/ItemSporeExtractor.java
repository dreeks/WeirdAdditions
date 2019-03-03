package xyz.dreeks.weirdadditions.items;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.dreeks.weirdadditions.WeirdAdditions;
import xyz.dreeks.weirdadditions.config.WAConfiguration;

import java.util.Random;

public class ItemSporeExtractor extends ItemBase {

    public ItemSporeExtractor() {
        super("spore_extractor");
        this.setMaxStackSize(1);
        this.setMaxDamage(WeirdAdditions.instance.config.SPOREEXTRACTOR_MAX_DAMAGE);
    }

    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer ep, EntityLivingBase target, EnumHand hand) {
        if (!ep.world.isRemote && target instanceof EntityCow) {
            Random rand = new Random();
            int i = rand.nextInt(WeirdAdditions.config.SPOREEXTRACTOR_LUCK);

            if (i == 0) {
                EntityItem ei = new EntityItem(target.world, target.posX, target.posY, target.posZ, new ItemStack(WAItems.sporeCow));
                target.world.spawnEntity(ei);
            }

            if (stack.getItemDamage() < WeirdAdditions.config.SPOREEXTRACTOR_MAX_DAMAGE) {
                stack.setItemDamage(stack.getItemDamage() + 1);
            } else {
                stack.setCount(0);
            }

            return true;
        }

        return false;
    }

}
