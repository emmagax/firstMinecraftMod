package net.emmanem.tutorialmod;

import net.emmanem.tutorialmod.block.ModBlocks;
import net.emmanem.tutorialmod.component.ModDataComponentTypes;
import net.emmanem.tutorialmod.effect.ModEffects;
import net.emmanem.tutorialmod.enchantment.ModEnchantmentEffects;
import net.emmanem.tutorialmod.item.ModItemGroups;
import net.emmanem.tutorialmod.item.ModItems;
import net.emmanem.tutorialmod.potion.ModPotions;
import net.emmanem.tutorialmod.sound.ModSounds;
import net.emmanem.tutorialmod.util.HammerUsageEvent;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.item.Items;
import net.minecraft.potion.Potions;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
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
		ModSounds.registerSounds();

		ModEffects.registerEffects();
		ModPotions.registerPotions();

		ModEnchantmentEffects.registerEnchantmentEffects();

		FuelRegistry.INSTANCE.add(ModItems.STARLIGHT_ASHES, 20000);

		PlayerBlockBreakEvents.BEFORE.register(new HammerUsageEvent());

		AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
			if (entity instanceof SheepEntity sheepEntity && !world.isClient()) {
				if (player.getMainHandStack().getItem() == Items.END_ROD) {
					player.sendMessage(Text.literal("You sick fuck"));
					player.getMainHandStack().decrement(1);
					sheepEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON,600, 6));
				}

				return ActionResult.PASS;
			}


			return ActionResult.PASS;
		});

		FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
			builder.registerPotionRecipe(Potions.AWKWARD, Items.SLIME_BALL, ModPotions.SLIMEY_POTION);
		});
	}
}