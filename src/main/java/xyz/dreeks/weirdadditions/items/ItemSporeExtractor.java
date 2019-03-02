package xyz.dreeks.weirdadditions.items;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.dreeks.weirdadditions.WeirdAdditions;

public class ItemSporeExtractor extends ItemBase {

    public ItemSporeExtractor() {
        super("sporeExtractor");
        this.setMaxStackSize(1);
        this.setMaxDamage(WeirdAdditions.instance.config.SPOREEXTRACTOR_MAX_DAMAGE);
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
        stack.setItemDamage(255);
        System.out.println("Dmg: " + stack.getItemDamage());
        if (worldIn.isRemote && stack.getItemDamage() > 0) {
            if (stack.getItemDamage() > 0) {
                stack.setItemDamage(stack.getItemDamage() - 1);
                System.out.println("Damage: " + stack.getItemDamage());

                return true;
            }
        }

        return false;
    }


}
