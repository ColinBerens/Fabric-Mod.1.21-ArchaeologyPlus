package net.dercoolin.archaeologyplus.item;

import net.dercoolin.archaeologyplus.Archaeology_plus;
import net.dercoolin.archaeologyplus.item.custom.AncientCompassItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    //public static final Item PINK_GARNET=registerItem("pink_garnet",new Item(new Item.Settings()));
    //public static final Item RAW_PINK_GARNET = registerItem("raw_pink_garnet", new Item (new Item.Settings()));

    public  static final Item ANCIENT_COMPASS = registerItem("ancient_compass", new AncientCompassItem (new Item.Settings()));

    private static Item registerItem(String name, Item item)
    {
        return Registry.register(Registries.ITEM, Identifier.of(Archaeology_plus.MOD_ID, name), item);
    }

    public static void registerModItems()
    {
        Archaeology_plus.LOGGER.info("RegisterItem for" + Archaeology_plus.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
            entries.add(ANCIENT_COMPASS);
        });
    }
}
