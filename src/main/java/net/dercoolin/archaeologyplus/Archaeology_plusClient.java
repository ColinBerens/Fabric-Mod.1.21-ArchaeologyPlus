package net.dercoolin.archaeologyplus;

import net.dercoolin.archaeologyplus.item.ModItems;
import net.fabricmc.api.ClientModInitializer;

public class Archaeology_plusClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ModItems.registerModItems();
    }
}
