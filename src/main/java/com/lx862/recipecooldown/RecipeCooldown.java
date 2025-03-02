package com.lx862.recipecooldown;

import com.lx862.recipecooldown.config.CooldownConfig;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.UUID;

public class RecipeCooldown implements ModInitializer {
    public static final Logger LOGGER = LogManager.getLogger("RecipeCooldown");
    public static final HashMap<UUID, Long> craftingCooldown = new HashMap<>();
    private static CooldownConfig config;

    @Override
    public void onInitialize() {
        config = new CooldownConfig();
    }

    public static CooldownConfig getConfig() {
        return config;
    }
}
