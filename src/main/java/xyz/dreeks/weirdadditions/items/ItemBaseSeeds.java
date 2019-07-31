package xyz.dreeks.weirdadditions.items;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSeeds;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.dreeks.weirdadditions.WeirdAdditions;
import xyz.dreeks.weirdadditions.blocks.BlockBaseCrop;

public class ItemBaseSeeds extends ItemSeeds {

    public ItemBaseSeeds(String unlocalizedName, Block crops, Block soil) {
        super(crops, soil);
        this.setRegistryName(unlocalizedName);
        this.setUnlocalizedName(unlocalizedName);
        this.setCreativeTab(WeirdAdditions.instance.creativeTab);
    }

    public ItemBaseSeeds(String unlocalizedName, BlockCrops block) {
        this(unlocalizedName, block, Blocks.FARMLAND);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        IBlockState plant = getPlant(worldIn, pos);

        Block block = worldIn.getBlockState(pos).getBlock();

        if (block.canSustainPlant(plant, worldIn, pos, facing, this)
        && block.isAir(worldIn.getBlockState(pos.up()), worldIn, pos.up())) {
            ItemStack seeds = player.getHeldItem(hand);

            worldIn.setBlockState(pos.up(), plant.withProperty(BlockBaseCrop.FACING, player.getHorizontalFacing()));
            seeds.setCount(seeds.getCount() - 1);

            return EnumActionResult.SUCCESS;
        }

        return EnumActionResult.FAIL;
    }
}
