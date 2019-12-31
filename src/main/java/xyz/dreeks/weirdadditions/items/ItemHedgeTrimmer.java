package xyz.dreeks.weirdadditions.items;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Inspired from ActuallyAdditions
 * I would only use ActuallyAdditions for this item so instead of installing it, I add it to our mod which is already installed in our modpack.
 * @TODO: Better craft. Create "blade" item and "handle" item which will be used for all our tools
 */
public class ItemHedgeTrimmer extends ItemBase {

    public ItemHedgeTrimmer() {
        super("hedge_trimmer");
        setMaxDamage(500);
    }

    protected ItemHedgeTrimmer(String name) {
        // @TODO: This is for extending the item, for the Electric Hedge trimmer
        super(name);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if (stack.hasTagCompound() && stack.getTagCompound().getInteger("mode") != 0) {
            switch(stack.getTagCompound().getInteger("mode")) {
                case 1:
                    tooltip.add(I18n.format("item.hedge_trimmer.mode.bushes", TextFormatting.ITALIC));
                    break;
                case 2:
                    tooltip.add(I18n.format("item.hedge_trimmer.mode.leaves", TextFormatting.ITALIC));
                    break;
            }
        } else {
            tooltip.add(I18n.format("item.hedge_trimmer.mode.default", TextFormatting.ITALIC));
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer ep, EnumHand hand) {
        if (!ep.isSneaking()) {
            ep.setActiveHand(hand);
            return new ActionResult<>(EnumActionResult.SUCCESS, ep.getHeldItem(hand));
        }

        NBTTagCompound tag = ep.getHeldItem(hand).getTagCompound();
        if (tag == null) {
            tag = new NBTTagCompound();
        }

        // Default to 0 :)
        int currentMode = tag.getInteger("mode");
        currentMode++;
        if (currentMode > 2) {
            currentMode = 0;
        }

        tag.setInteger("mode", currentMode);

        ep.getHeldItem(hand).setTagCompound(tag);
        return new ActionResult<>(EnumActionResult.SUCCESS, ep.getHeldItem(hand));
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.BOW;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return Integer.MAX_VALUE;
    }

    @Override
    public void onUsingTick(ItemStack stack, EntityLivingBase ep, int time) {
        int x = MathHelper.floor(ep.posX);
        int y = MathHelper.floor(ep.posY);
        int z = MathHelper.floor(ep.posZ);

        if (!ep.world.isRemote) {
            if (time <= this.getMaxItemUseDuration(stack) && this.canBeUsed(stack)) {
                ArrayList<BlockPos> blockList = new ArrayList<>();
                for (int i = -3; i < 3; i++) {
                    for (int j = -5; j < 5; j++) {
                        for (int k = -3; k < 3; k++) {
                            BlockPos bp = new BlockPos(x + i, y + j, z + k);
                            IBlockState bs = ep.world.getBlockState(bp);

                            if (this.isCorrectBlock(stack, bs.getBlock())) {
                                blockList.add(bp);
                            }
                        }
                    }
                }

                if (!blockList.isEmpty()) {
                    Collections.shuffle(blockList);
                    BlockPos bp = blockList.get(0);
                    IBlockState bs = ep.world.getBlockState(bp);

                    bs.getBlock().dropBlockAsItem(ep.world, bp, bs, 0);
                    ep.world.playEvent(2001, bp, Block.getStateId(bs));
                    ep.world.setBlockToAir(bp);

                    this.damageItem(stack, ep);
                }
            }
        }
    }

    private boolean isCorrectBlock(ItemStack is, Block b) {
        if (b != null) {
            boolean hasMode = is.hasTagCompound() && is.getTagCompound().hasKey("mode");
            if (!hasMode)
                return b instanceof BlockBush || b instanceof IShearable;

            int mode = is.getTagCompound().getInteger("mode");
            switch (mode) {
                case 0:
                    return b instanceof BlockBush || b instanceof IShearable;
                case 1:
                    return b instanceof BlockBush;
                case 2:
                    return !(b instanceof BlockBush) && b instanceof IShearable;
            }
        }
        return false;
    }

    private boolean canBeUsed(ItemStack stack) {
        return stack.getItemDamage() < this.getMaxDamage(stack);
    }

    private void damageItem(ItemStack stack, EntityLivingBase ep) {
        stack.setItemDamage(stack.getItemDamage() + 1);
        if (stack.getItemDamage() >= stack.getMaxDamage()) {
            stack.setCount(0);
            ep.world.playSound(null, ep.getPosition().getX(), ep.getPosition().getY(), ep.getPosition().getZ(), SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.PLAYERS, 0.7f, 0.001f);
        }
    }

}
