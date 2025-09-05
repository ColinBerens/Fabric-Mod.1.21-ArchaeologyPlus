package net.dercoolin.archaeologyplus;

import net.dercoolin.archaeologyplus.block.ModBlocks;
import net.dercoolin.archaeologyplus.block.custom.SacrificeBlock;
import net.dercoolin.archaeologyplus.item.ModItems;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Archaeology_plus implements ModInitializer {
	public static final String MOD_ID = "archaeologyplus";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        //ModItemGroup.registerItemGroups();
        ModItems.registerModItems();
        ModBlocks.registerModBlocks();

        ServerLivingEntityEvents.AFTER_DEATH.register((LivingEntity entity, DamageSource source) -> {
            if (entity.getWorld() instanceof ServerWorld serverWorld) {
                // Only run for fire-related deaths
                if (source.isOf(DamageTypes.IN_FIRE) || source.isOf(DamageTypes.ON_FIRE) || source.isOf(DamageTypes.LAVA)) {
                    checkForSacrificeBlocks(serverWorld, entity);
                }
            }
        });
    }
    private void checkForSacrificeBlocks(ServerWorld world, Entity entity) {
        // Check in a 3x3 area around and below the entity position
        BlockPos entityPos = entity.getBlockPos();
        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                for (int y = -3; y <= 1; y++) { // Check 3 blocks below to 1 block above
                    BlockPos checkPos = entityPos.add(x, y, z);
                    Block block = world.getBlockState(checkPos).getBlock();

                    if (block instanceof SacrificeBlock sacrificeBlock) {
                        sacrificeBlock.onEntityKilledAbove(world, checkPos, entity);
                    }
                }
            }
        }
    }
}