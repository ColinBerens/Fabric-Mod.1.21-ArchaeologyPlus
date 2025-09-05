package net.dercoolin.archaeologyplus.block.custom;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class SacrificeBlock extends Block {

    public int weatherDuration = 3600; // Duration in ticks (5 seconds)
    public SacrificeBlock(Settings settings) {
        super(settings);
    }

    public void onEntityKilledAbove(ServerWorld world, BlockPos blockPos, Entity entity) {
        // Your custom method logic here
        performSacrificeRitual(world, blockPos, entity);
    }

    private void performSacrificeRitual(ServerWorld world, BlockPos blockPos, Entity entity) {
        // Example ritual effects - customize as needed
        BlockPos entityPos = entity.getBlockPos();
        // 1. Spawn particles
        for (int i = 0; i < 20; i++) {
            double offsetX = world.random.nextGaussian() * 0.5;
            double offsetY = world.random.nextDouble() * 2.0;
            double offsetZ = world.random.nextGaussian() * 0.5;

            world.spawnParticles(
                    ParticleTypes.SOUL,
                    blockPos.getX() + 0.5 + offsetX,
                    blockPos.getY() + 1.0 + offsetY,
                    blockPos.getZ() + 0.5 + offsetZ,
                    1, 0, 0.1, 0, 0.02
            );
        }

        // 2. Play sound effect
        world.playSound(
                null,
                blockPos,
                SoundEvents.ENTITY_WITHER_SPAWN,
                SoundCategory.BLOCKS,
                1.0f,
                0.8f
        );

        // 3. Send message to nearby players
        world.getPlayers().forEach(player -> {
            if (player.squaredDistanceTo(blockPos.getX(), blockPos.getY(), blockPos.getZ()) < 100) {
                if (player instanceof ServerPlayerEntity serverPlayer) {
                    serverPlayer.sendMessage(
                            Text.literal("§5The sacrifice has been accepted..."),
                            true // Show as action bar
                    );
                }
            }
        });

        ChangeToSpecificWeather(entity, world);


        // 4. Additional custom logic
        // Add your own ritual effects here:
        // - Grant experience to nearby players
        // - Spawn items
        // - Change nearby blocks
        // - Trigger redstone signal
        // - etc.

        System.out.println("Sacrifice ritual performed at " + blockPos + " for entity killed at " + entityPos);
    }

    private void ChangeToSpecificWeather(Entity entity, ServerWorld world) {
        {
            if (entity instanceof PlayerEntity) {
                WeatherController.setThunderstorm(world, weatherDuration);
            }
            if (entity instanceof HostileEntity) {
                WeatherController.setRainyWeather(world, weatherDuration);
            }
            if (entity instanceof AnimalEntity) {
                WeatherController.setClearWeather(world, weatherDuration);
            }
        }
    }
}
