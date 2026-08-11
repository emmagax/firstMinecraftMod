package net.emmanem.tutorialmod;

import net.emmanem.tutorialmod.block.ModBlocks;
import net.emmanem.tutorialmod.component.ModDataComponentTypes;
import net.emmanem.tutorialmod.item.ModItemGroups;
import net.emmanem.tutorialmod.item.ModItems;
import net.emmanem.tutorialmod.util.HammerUsageEvent;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Initializer
public class TutorialMod implements ModInitializer {
	public static final String MOD_ID = "tutorialmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


	// Initialize mod classes
	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		ModDataComponentTypes.registerDataComponentTypes();

		FuelRegistry.INSTANCE.add(ModItems.STARLIGHT_ASHES, 20000);

		PlayerBlockBreakEvents.BEFORE.register(new HammerUsageEvent());
	}
}