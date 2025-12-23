package net.emmanem.tutorialmod.item.custom;

import net.emmanem.tutorialmod.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

import java.util.Map;
import java.util.stream.Collectors;

public class ChiselItem extends Item {
    private static final Map<Block, Block> CHISEL_MAP =
            Map.of(
                    Blocks.STONE, Blocks.STONE_BRICKS,
                    Blocks.END_STONE, Blocks.END_STONE_BRICKS,
                    Blocks.OAK_LOG, ModBlocks.PINK_GARNET_BLOCK,
                    Blocks.GOLD_BLOCK, Blocks.NETHERITE_BLOCK
            );

    private static final Map<Block, Block> REVERSE_CHISEL_MAP =
            Map.of(
                    Blocks.STONE_BRICKS, Blocks.STONE,
                    Blocks.END_STONE_BRICKS, Blocks.END_STONE,
                    ModBlocks.PINK_GARNET_BLOCK, Blocks.OAK_LOG,
                    Blocks.NETHERITE_BLOCK, Blocks.GOLD_BLOCK
            );

    public ChiselItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        Block clickedBlock = world.getBlockState(context.getBlockPos()).getBlock();
        PlayerEntity player = context.getPlayer();

        if (player == null) {
            return ActionResult.PASS;
        }

        // Choose which map to use based on sneaking
        Map<Block, Block> mapToUse = player.isSneaking() ? REVERSE_CHISEL_MAP : CHISEL_MAP;

        if (mapToUse.containsKey(clickedBlock)) {
            if (!world.isClient()) {
                Block newBlock = mapToUse.get(clickedBlock);

                world.setBlockState(context.getBlockPos(), newBlock.getDefaultState());

                context.getStack().damage(1, ((ServerWorld) world), ((ServerPlayerEntity) player),
                        item -> player.sendEquipmentBreakStatus(item, EquipmentSlot.MAINHAND));

                world.playSound(null, context.getBlockPos(), SoundEvents.BLOCK_GRINDSTONE_USE, SoundCategory.BLOCKS);
            }
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }
}
