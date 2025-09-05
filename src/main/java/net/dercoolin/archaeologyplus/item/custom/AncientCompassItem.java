package net.dercoolin.archaeologyplus.item.custom;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextContent;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.GlobalPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;

import java.util.List;

public class AncientCompassItem extends Item {

    private static final String TARGET_KEY = "AncientTarget";

    public AncientCompassItem(Settings settings) {
        super(settings);
    }


    @Override
    public ActionResult useOnBlock(ItemUsageContext context)
    {
        World world = context.getWorld();
        if (!world.isClient) {
            PlayerEntity player = context.getPlayer();
            if (player != null) {
                // Convert to BlockPos
                BlockPos targetBlockPos = context.getBlockPos();
                LightingAtPosition(targetBlockPos,world);
                player.sendMessage(Text.literal("Lightning summoned at " + world.getTopY(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, targetBlockPos.getX(),targetBlockPos.getZ())), false);
            }
        }
        return ActionResult.SUCCESS;
    }

    private void LightingAtPosition(BlockPos targetBlockPos, World world)
    {
//        LightningEntity lightning = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
//        lightning.setPosition(targetBlockPos.getX(), targetBlockPos.getY(), targetBlockPos.getZ());
//        world.spawnEntity(lightning);
    }

    private GlobalPos createRandomTarget(World world)
    {
        Random random = world.getRandom();

        // Example: pick random coords within a large range
        int x = random.nextInt(200) - 100;
        int z = random.nextInt(200) - 100;
        int y = world.getTopY(Heightmap.Type.WORLD_SURFACE, x,z); // safest Y (surface)

        return GlobalPos.create(world.getRegistryKey(), new BlockPos(x, y, z));
    }
}

