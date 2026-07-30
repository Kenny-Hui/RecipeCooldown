package com.lx862.recipecooldown.config;

import folk.sisby.kaleido.api.ReflectiveConfig;
import folk.sisby.kaleido.lib.quiltconfig.api.annotations.Comment;
import folk.sisby.kaleido.lib.quiltconfig.api.values.TrackedValue;
import net.fabricmc.loader.api.FabricLoader;
import org.jetbrains.annotations.Range;

public class Config extends ReflectiveConfig {
    public static final Config INSTANCE = createToml(FabricLoader.getInstance().getConfigDir(), "", "recipecooldown", Config.class);

    @Comment("Whether to enable cooldown.")
    public final TrackedValue<Boolean> enabled = value(true);

    @Comment("The minimum cooldown required in millisecond for crafting recipe.")
    @Range(from = 1, to = 10000)
    public final TrackedValue<Long> cooldownMs = value(50L); // Base MSPT for server (20 TPS)

    public static void init() {
        // static init
    }
}
