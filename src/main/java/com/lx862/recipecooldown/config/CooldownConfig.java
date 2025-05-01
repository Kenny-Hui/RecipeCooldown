package com.lx862.recipecooldown.config;

import com.lx862.recipecooldown.RecipeCooldown;
import net.fabricmc.loader.api.FabricLoader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CooldownConfig {
    private static final Path CONFIG_PATH = Paths.get(FabricLoader.getInstance().getConfigDir().toString(), "recipe_cooldown.cfg");
    private int cooldownMillis = 50; // Base MSPT for server (20 TPS)

    public CooldownConfig() {
        load();
    }

    public void load() {
        if (Files.exists(CONFIG_PATH)) {
            try(BufferedReader br = new BufferedReader(new FileReader(CONFIG_PATH.toFile()))) {
                String millisStr = br.readLine().trim();
                this.cooldownMillis = Integer.parseInt(millisStr);
            } catch (Exception e) {
                RecipeCooldown.LOGGER.error("[RecipeCooldown] Failed to read config file!", e);
            }
        } else {
            generate();
        }
        RecipeCooldown.LOGGER.info("[RecipeCooldown] Cooldown set to {} ms", this.cooldownMillis);
    }

    public void generate() {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(CONFIG_PATH.toFile()))) {
            bw.write(String.valueOf(cooldownMillis));
        } catch (Exception e) {
            RecipeCooldown.LOGGER.error("[RecipeCooldown] Failed to generate config file!", e);
        }
    }

    public int getCooldownMillis() {
        return this.cooldownMillis;
    }
}
