package net.emmanem.tutorialmod.world.tree;

import net.emmanem.tutorialmod.TutorialMod;
import net.emmanem.tutorialmod.world.ModConfiguredFeatures;
import net.minecraft.block.SaplingGenerator;

import java.util.Optional;

public class ModSaplingGenerator {
    public  static final SaplingGenerator DRIFTWOOD = new SaplingGenerator(TutorialMod.MOD_ID + ":driftwood",
            Optional.empty(), Optional.of(ModConfiguredFeatures.DRIFTWOOD_KEY), Optional.empty());
}
