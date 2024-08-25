package com.lx862.recipecooldown.config;

import com.lx862.recipecooldown.RecipeCooldown;
import net.fabricmc.loader.api.FabricLoader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CooldownConfig {
    private static final Path CONFIG_PATH = Paths.get(FabricLoader.getInstance().getConfigDir().toString(), "recipe_cooldown.cfg");
    public static int timeoutMs = 100;

    public static void load() {
        if (Files.exists(CONFIG_PATH)) {
            try {
                BufferedReader brTest = new BufferedReader(new FileReader(CONFIG_PATH.toFile()));
                String msStr = brTest.readLine().trim();
                timeoutMs = Integer.parseInt(msStr);
                RecipeCooldown.LOGGER.info("[RecipeCooldown] Cooldown set to " + timeoutMs + "ms");
            } catch (Exception e) {}
        }
    }
}
