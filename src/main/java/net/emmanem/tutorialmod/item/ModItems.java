package net.emmanem.tutorialmod.item;

import net.emmanem.tutorialmod.TutorialMod;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    // Add Pink Garnet
    public static final Item PINK_GARNET = registerItem("pink_garnet",
            new Item(new Item.Settings()));

    // Add Raw Pink Garnet
    public static final Item RAW_PINK_GARNET = registerItem("raw_pink_garnet",
            new Item(new Item.Settings()));

    // HELPERS
    // Register Items
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(TutorialMod.MOD_ID, name), item);
    }

    // Registering Items (add onInitialize)
    public static void registerModItems() {
        TutorialMod.LOGGER.info("Registering Mod Items for " + TutorialMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(PINK_GARNET);
            entries.add(RAW_PINK_GARNET);
        });
    }
}