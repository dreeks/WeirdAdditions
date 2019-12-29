package xyz.dreeks.weirdadditions.items;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Inspired from ActuallyAdditions
 * Just because I don't want to have mods I use for only one item in my modpack, I've implemented it in my own mod
 */
public class ItemHedgeTrimmer extends ItemBase {

    public ItemHedgeTrimmer() {
        super("hedge_trimmer");
        setMaxDamage(500);
    }

    /**
     *  Temporarly stolen from Ellpeck/ActuallyAdditions
     *  Need a clean rewrite when I have time
     */

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        player.setActiveHand(hand);
        return new ActionResult<>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
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
    public void onUsingTick(ItemStack stack, EntityLivingBase player, int time) {
        this.doUpdate(player.world, MathHelper.floor(player.posX), MathHelper.floor(player.posY), MathHelper.floor(player.posZ), time, stack);
    }

    private boolean doUpdate(World world, int x, int y, int z, int time, ItemStack stack) {
        if (!world.isRemote) {
            if (time <= this.getMaxItemUseDuration(stack) && stack.getItemDamage() < this.getMaxDamage(stack)) {
                // Breaks the Blocks
                boolean broke = this.breakStuff(world, x, y, z);

                // Plays a Minecart sounds (It really sounds like a Leaf Blower!)
                world.playSound(null, x, y, z, SoundEvents.ENTITY_MINECART_RIDING, SoundCategory.PLAYERS, 0.3F, 0.001F);

                // @TODO: find a correct amount
                if (broke) {
                    stack.setItemDamage(stack.getItemDamage() + 1);
                    if (stack.getItemDamage() >= stack.getMaxDamage()) {
                        stack.setCount(0);
                    }
                }

                return broke;
            }
        }
        return false;
    }

    public boolean breakStuff(World world, int x, int y, int z) {
        ArrayList<BlockPos> breakPositions = new ArrayList<>();

        int rangeSides = 5;
        for (int reachX = -rangeSides; reachX < rangeSides + 1; reachX++) {
            for (int reachZ = -rangeSides; reachZ < rangeSides + 1; reachZ++) {
                for (int reachY = -rangeSides; reachY < rangeSides + 1; reachY++) {
                    //The current Block to break
                    BlockPos pos = new BlockPos(x + reachX, y + reachY, z + reachZ);
                    Block block = world.getBlockState(pos).getBlock();

                    if (block != null && (block instanceof BlockBush || block instanceof IShearable)) {
                        breakPositions.add(pos);
                    }
                }
            }
        }

        if (!breakPositions.isEmpty()) {
            Collections.shuffle(breakPositions);

            BlockPos theCoord = breakPositions.get(0);
            IBlockState theState = world.getBlockState(theCoord);

            theState.getBlock().dropBlockAsItem(world, theCoord, theState, 0);
            //Plays the Breaking Sound
            world.playEvent(2001, theCoord, Block.getStateId(theState));

            //Deletes the Block
            world.setBlockToAir(theCoord);

            return true;
        }
        return false;
    }

    //@Override
    public boolean update(ItemStack stack, TileEntity tile, int elapsedTicks) {
        return this.doUpdate(tile.getWorld(), tile.getPos().getX(), tile.getPos().getY(), tile.getPos().getZ(), elapsedTicks, stack);
    }

    //@Override
    public int getUsePerTick(ItemStack stack, TileEntity tile, int elapsedTicks) {
        return 60;
    }
}
