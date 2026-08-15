package net.emmanem.tutorialmod.world.gen;

import net.emmanem.tutorialmod.entity.ModEntities;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnLocation;
import net.minecraft.entity.SpawnLocationTypes;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;

public class ModEntitySpawns {
    public static void addSpawns() {
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.ERODED_BADLANDS, BiomeKeys.BADLANDS, BiomeKeys.WOODED_BADLANDS),
                SpawnGroup.AMBIENT, ModEntities.MANTIS, 90, 1, 2);

        SpawnRestriction.register(ModEntities.MANTIS, SpawnLocationTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                (entityType, world, spawnReason, pos, random) -> {
                    boolean isValidFloor = world.getBlockState(pos.down()).isIn(BlockTags.ARMADILLO_SPAWNABLE_ON);
                    boolean hasGoodLight = world.getLightLevel(pos) >= 9;
                    return isValidFloor && hasGoodLight;
                }
        );
    }
}
