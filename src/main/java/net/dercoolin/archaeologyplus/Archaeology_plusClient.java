package net.dercoolin.archaeologyplus;

import net.fabricmc.api.ClientModInitializer;

public class Archaeology_plusClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ModItems.registerModItems();
    }
}
