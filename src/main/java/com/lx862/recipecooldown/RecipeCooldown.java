package com.lx862.recipecooldown;

import com.lx862.recipecooldown.config.Config;
import com.lx862.recipecooldown.config.LegacyConfig;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RecipeCooldown implements ModInitializer {
    public static final Logger LOGGER = LogManager.getLogger("RecipeCooldown");
    public static final Map<UUID, Long> craftingCooldown = new HashMap<>();

    @Override
    public void onInitialize() {
        Config.init();
        LegacyConfig.migrate();

        if(Config.INSTANCE.enabled.value()) {
            LOGGER.info("[RecipeCooldown] Cooldown set to {} ms", Config.INSTANCE.cooldownMs.value());
        } else {
            LOGGER.info("[RecipeCooldown] Cooldown is disabled, you may re-enable in config.");
        }
    }
}
