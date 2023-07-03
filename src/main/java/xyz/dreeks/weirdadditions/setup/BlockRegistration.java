package xyz.dreeks.weirdadditions.setup;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import xyz.dreeks.weirdadditions.blocks.CowCropBlock;
import xyz.dreeks.weirdadditions.blocks.FishCropBlock;
import xyz.dreeks.weirdadditions.utils.Constants;

import net.minecraft.world.level.block.Block;

public class BlockRegistration {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Constants.MOD_ID);

    public static final RegistryObject<Block> CROPS_COW = BLOCKS.register("crops_cow", () -> new CowCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion()));
    public static final RegistryObject<Block> CROPS_FISH = BLOCKS.register("crops_fish", () -> new FishCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion()));

    public static void init() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
