package net.iwyo.testmod.event;

import java.util.Collection;
import java.util.Iterator;

import com.mojang.authlib.GameProfile;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;

import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;


import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {
    public static final String KEY_CATEGORY_TUTORIAL = "key.category.tutorialmod.tutorial";
    public static final String KEY_DRINK_WATER = "key.tutorialmod.drink_water";
    public static KeyBinding drinkingKey;

    public static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if(drinkingKey.wasPressed())
            {
                if (client.player != null) {
                    Collection<PlayerListEntry> playerList = client.player.networkHandler.getPlayerList();

                    Iterator<PlayerListEntry> items = playerList.iterator();
                    while (items.hasNext()){
                        PlayerListEntry item = items.next();
                        GameProfile profile = item.getProfile();
                        String name = profile.getName();

                        client.player.sendMessage(Text.of(name),false);
                    }
                }
                // This happens when our custom key is pressed
                //client.player.sendMessage(Text.of("Hello I pressed a Key"),false);
                //client.player.move(MovementType.SELF, new Vec3d(5,0,0));
            }
        });
    }

    public static void register() {
        drinkingKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_DRINK_WATER,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_O,
                KEY_CATEGORY_TUTORIAL
        ));
        registerKeyInputs();
    }
}
