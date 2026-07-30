package com.lx862.recipecooldown.config;

import com.lx862.recipecooldown.RecipeCooldown;
import net.fabricmc.loader.api.FabricLoader;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LegacyConfig {
    private static final Path CONFIG_PATH = Paths.get(FabricLoader.getInstance().getConfigDir().toString(), "recipe_cooldown.cfg");

    public static void migrate() {
        if (Files.exists(CONFIG_PATH)) {
            try(BufferedReader br = new BufferedReader(new FileReader(CONFIG_PATH.toFile()))) {
                String millisStr = br.readLine().trim();
                Config.INSTANCE.cooldownMs.setValue(Long.parseLong(millisStr));
            } catch (Exception e) {
                RecipeCooldown.LOGGER.error("[RecipeCooldown] Failed to read config file!", e);
            }
            try {
                Files.delete(CONFIG_PATH);
            } catch (IOException e) {
                RecipeCooldown.LOGGER.error("[RecipeCooldown] Failed to cleanup old config file!", e);
            }
        }
    }
}
