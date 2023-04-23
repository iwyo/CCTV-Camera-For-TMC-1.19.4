package net.iwyo.testmod;

import net.fabricmc.api.ClientModInitializer;
import net.iwyo.testmod.event.KeyInputHandler;

public class TestModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        KeyInputHandler.register();

    }
}
