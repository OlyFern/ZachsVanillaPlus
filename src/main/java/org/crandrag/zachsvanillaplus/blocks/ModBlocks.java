package org.crandrag.zachsvanillaplus.blocks;

import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.crandrag.zachsvanillaplus.items.ModItems;
import org.crandrag.zachsvanillaplus.main;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(main.MODID);

    public static final DeferredBlock<Block> OAK_MOSAIC = registerFlammableBlock("oak_mosaic", Blocks.OAK_PLANKS, 20, 5);

    //TODO: Change baseBlock to Properties in case I want custom properties for a block
    private static DeferredBlock<Block> registerFlammableBlock(String name, Block baseBlock, int flammability, int fireSpreadSpeed) {
        return registerBlock(
                name,
                () -> new Block(BlockBehaviour.Properties.ofFullCopy(baseBlock).setId(blockID(name))) {
                    @Override
                    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                        return true;
                    }

                    @Override
                    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                        return flammability;
                    }

                    @Override
                    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                        return fireSpreadSpeed;
                    }
                });
    }

    private static ResourceKey<Block> blockID(String name){
        return ResourceKey.create(Registries.BLOCK, ResourceLocation.parse(String.format("%s:%s", main.MODID, name)));
    }

    private static <T extends Block> DeferredBlock<Block> registerBlock(String name, Supplier<T> block){
        DeferredBlock<Block> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<Block> block){
        ModItems.ITEMS.registerSimpleBlockItem(name, block);
    }
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
