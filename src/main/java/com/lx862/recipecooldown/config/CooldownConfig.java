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
    private int cooldownMs = 100;

    public CooldownConfig() {
        load();
    }

    public void load() {
        if (Files.exists(CONFIG_PATH)) {
            try {
                BufferedReader brTest = new BufferedReader(new FileReader(CONFIG_PATH.toFile()));
                String msStr = brTest.readLine().trim();
                this.cooldownMs = Integer.parseInt(msStr);
            } catch (Exception e) {}
        }
        RecipeCooldown.LOGGER.info("[RecipeCooldown] Cooldown set to {} ms", this.cooldownMs);
    }

    public int getCooldownMs() {
        return this.cooldownMs;
    }
}
