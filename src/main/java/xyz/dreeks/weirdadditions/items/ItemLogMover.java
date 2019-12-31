package xyz.dreeks.weirdadditions.items;

import ic2.core.block.BlockRubWood;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

public class ItemLogMover extends ItemBase {

    public ItemLogMover() {
        super("logmover");
        setMaxDamage(20);
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelResourceLocation emptyModel = new ModelResourceLocation(getRegistryName(), "inventory");
        ModelResourceLocation filledModel = new ModelResourceLocation(getRegistryName() + "_filled", "inventory");

        ModelBakery.registerItemVariants(this, emptyModel, filledModel);

        ModelLoader.setCustomMeshDefinition(this, stack -> {
            if (stack.hasTagCompound() && stack.getTagCompound().getBoolean("hasBlock")) {
                return filledModel;
            } else {
                return emptyModel;
            }
        });
    }


    @Override
    public EnumActionResult onItemUse(EntityPlayer ep, World w, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!w.isRemote) {
            IBlockState bs = w.getBlockState(pos);
            Block blockRubber = Block.getBlockFromName("ic2:rubber_wood");

            ItemStack is = ep.getHeldItem(hand);
            NBTTagCompound tag = is.getTagCompound();
            if (!is.hasTagCompound()) {
                tag = new NBTTagCompound();
            }

            // If the player is sneaking, we rotate the cube
            if (blockRubber != null) {
                if (!ep.isSneaking() && bs.getBlock() == blockRubber) {
                    BlockRubWood.RubberWoodState rws = ItemLogMover.getRWS(bs);
                    BlockRubWood.RubberWoodState newValue = null;

                    if (rws != null) {
                        // We set the rotated position
                        switch (rws) {
                            case dry_east:
                                newValue = BlockRubWood.RubberWoodState.dry_north;
                                break;
                            case dry_north:
                                newValue = BlockRubWood.RubberWoodState.dry_west;
                                break;
                            case dry_west:
                                newValue = BlockRubWood.RubberWoodState.dry_south;
                                break;
                            case dry_south:
                                newValue = BlockRubWood.RubberWoodState.dry_east;
                                break;
                            case wet_east:
                                newValue = BlockRubWood.RubberWoodState.wet_north;
                                break;
                            case wet_north:
                                newValue = BlockRubWood.RubberWoodState.wet_west;
                                break;
                            case wet_west:
                                newValue = BlockRubWood.RubberWoodState.wet_south;
                                break;
                            case wet_south:
                                newValue = BlockRubWood.RubberWoodState.wet_east;
                                break;
                        }

                        // We then set this value to the block
                        PropertyEnum<BlockRubWood.RubberWoodState> stateProperty = PropertyEnum.create("state", BlockRubWood.RubberWoodState.class);
                        w.setBlockState(pos, bs.withProperty(stateProperty, newValue));
                    }
                } else {
                    if (tag.getBoolean("hasBlock")) {
                        PropertyEnum<BlockRubWood.RubberWoodState> stateProperty = PropertyEnum.create("state", BlockRubWood.RubberWoodState.class);
                        BlockRubWood.RubberWoodState rws = ItemLogMover.getRWSFromString(tag.getString("state"));

                        if (rws != null) {
                            if (ItemLogMover.placeBlock(w, facing, pos, blockRubber.getDefaultState().withProperty(stateProperty, rws))) {
                                tag.removeTag("state");
                                tag.setBoolean("hasBlock", false);
                                is.setItemDamage(is.getItemDamage() + 1);
                                if (is.getItemDamage() >= is.getMaxDamage()) {
                                    is.setCount(0);
                                }
                            }
                        }
                    } else if (bs.getBlock() == blockRubber) {
                        BlockRubWood.RubberWoodState rws = ItemLogMover.getRWS(bs);
                        String state = ItemLogMover.getStringFromRWS(rws);

                        if (state != null && !state.isEmpty()) {
                            tag.setBoolean("hasBlock", true);
                            if (rws != null) {
                                tag.setString("state", state);
                                ItemLogMover.removeBlock(w, pos);
                            }
                        }
                    }

                    is.setTagCompound(tag);
                }
            }
        }

        return EnumActionResult.PASS;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack is, @Nullable World w, List<String> tooltip, ITooltipFlag flagIn) {
        if (is.hasTagCompound()) {
            NBTTagCompound tag = is.getTagCompound();

            if (tag.hasKey("hasBlock") && tag.getBoolean("hasBlock")) {
                tooltip.add(tag.getString("state"));
            }
        }
    }

    private static BlockRubWood.RubberWoodState getRWS(IBlockState bs) {
        for (Map.Entry<IProperty<?>, Comparable<?>> p : bs.getProperties().entrySet()){
            // We're looking for the "state" property
            if(p.getKey().getName().equals("state")){
                return (BlockRubWood.RubberWoodState)p.getValue();
            }
        }

        return null;
    }

    private static String getStringFromRWS(BlockRubWood.RubberWoodState rws) {
        switch (rws) {
            case dry_west:
                return "Dry (West)";
            case dry_east:
                return "Dry (East)";
            case dry_south:
                return "Dry (South)";
            case dry_north:
                return "Dry (North)";

            case wet_west:
                return "Wet (West)";
            case wet_east:
                return "Wet (East)";
            case wet_south:
                return "Wet (South)";
            case wet_north:
                return "Wet (North)";
        }

        return null;
    }

    private static BlockRubWood.RubberWoodState getRWSFromString(String s) {
        switch (s.toLowerCase()) {
            case "dry (west)":
                return BlockRubWood.RubberWoodState.dry_west;
            case "dry (east)":
                return BlockRubWood.RubberWoodState.dry_east;
            case "dry (south)":
                return BlockRubWood.RubberWoodState.dry_south;
            case "dry (north)":
                return BlockRubWood.RubberWoodState.dry_north;

            case "wet (west)":
                return BlockRubWood.RubberWoodState.wet_west;
            case "wet (east)":
                return BlockRubWood.RubberWoodState.wet_east;
            case "wet (south)":
                return BlockRubWood.RubberWoodState.wet_south;
            case "wet (north)":
                return BlockRubWood.RubberWoodState.wet_north;
        }

        return null;
    }

    private static void removeBlock(World w, BlockPos bp) {
        IBlockState bs = w.getBlockState(bp);
        w.setBlockToAir(bp);
        w.notifyBlockUpdate(bp, bs, w.getBlockState(bp), 0);
    }

    private static boolean placeBlock(World w, EnumFacing f, BlockPos bp, IBlockState bs) {
        boolean resp = false;
        BlockPos pos = null;

        switch (f) {
            case UP:
                pos = bp.up();
                break;
            case DOWN:
                pos = bp.down();
                break;
            case WEST:
                pos = bp.west();
                break;
            case EAST:
                pos = bp.east();
                break;
            case NORTH:
                pos = bp.north();
                break;
            case SOUTH:
                pos = bp.south();
                break;
        }


        if (pos != null) {
            IBlockState old = w.getBlockState(pos);
            resp = w.setBlockState(pos, bs);
            w.notifyBlockUpdate(pos, old, bs, 0);
        }

        return resp;
    }

}
