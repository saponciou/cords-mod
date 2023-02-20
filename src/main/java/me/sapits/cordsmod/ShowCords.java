package me.sapits.cordsmod;


import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;
public class ShowCords implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        /*
          This code runs as soon as Minecraft is in a mod-load-ready state.
          However, some things (like resources) may still be unitialized.
          Proceed with mild caution.
         */
        System.out.println("Debug");
        this.registerKeybindings();
    }

    void registerKeybindings() {
        KeyBinding toggleHudKey = new KeyBinding(
                "key.simple_utilities.toggle_hud",
                GLFW.GLFW_KEY_K,
                "key.category.simple_utilities.hud"
        );

        KeyBinding toggleHudKeybinding = KeyBindingHelper.registerKeyBinding(toggleHudKey);

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;

            if (toggleHudKeybinding.wasPressed()) {

                String chatMessage = "key.simple_utilities.toggle_hud.chat_message.on";

                client.player.sendMessage(Text.translatable(chatMessage), true);
            }
        });
    }
}
