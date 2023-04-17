package com.lx.recipecooldown.mixin;

import com.lx.recipecooldown.Config.CooldownConfig;
import com.lx.recipecooldown.RecipeCooldown;
import net.minecraft.network.packet.c2s.play.CraftRequestC2SPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayNetworkHandler.class)
public class ServerPlayNetworkHandlerMixin {
    @Shadow public ServerPlayerEntity player;

    @Inject(method = "onCraftRequest", at = @At("HEAD"), cancellable = true)
    public void onCraftRequestStart(CraftRequestC2SPacket packet, CallbackInfo ci) {
        long tm = System.currentTimeMillis() - RecipeCooldown.craftingCooldown.getOrDefault(player.getUuid(), 0L);
        if(tm <= CooldownConfig.timeoutMs) {
            ci.cancel();
        }
    }

    @Inject(method = "onCraftRequest", at = @At("TAIL"))
    public void onCraftRequest(CraftRequestC2SPacket packet, CallbackInfo ci) {
        RecipeCooldown.craftingCooldown.put(player.getUuid(), System.currentTimeMillis());
    }
}