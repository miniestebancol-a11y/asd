package com.ejemplo.boton4;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import org.lwjgl.glfw.GLFW;

public class Boton4Client implements ClientModInitializer {

    private static boolean yaPresionado = false;

    @Override
    public void onInitializeClient() {
        ScreenEvents.AFTER_INIT.register((client, screen, ancho, alto) -> {
            if (screen instanceof InventoryScreen && !yaPresionado) {
                yaPresionado = true;
                client.execute(() -> {
                    InputConstants.Key tecla =
                        InputConstants.Type.MOUSE.getOrCreate(GLFW.GLFW_MOUSE_BUTTON_4);
                    KeyMapping.click(tecla);
                });
            }

            // Se resetea cuando se cierra la pantalla
            ScreenEvents.remove(screen).register(s -> yaPresionado = false);
        });
    }
}
