package com.lx862.recipecooldown.mixin;

import com.lx862.recipecooldown.RecipeCooldown;
import net.minecraft.network.protocol.game.ServerboundPlaceRecipePacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerGamePacketListenerImpl.class)
public class ServerPlayNetworkHandlerMixin {
    @Shadow public ServerPlayer player;

    @Inject(method = "handlePlaceRecipe", at = @At("HEAD"), cancellable = true)
    public void onCraftRequestStart(ServerboundPlaceRecipePacket packet, CallbackInfo ci) {
        long cooldown = System.currentTimeMillis() - RecipeCooldown.craftingCooldown.getOrDefault(player.getUUID(), 0L);
        if(cooldown <= RecipeCooldown.getConfig().getCooldownMillis()) {
            ci.cancel();
        }
    }

    @Inject(method = "handlePlaceRecipe", at = @At("TAIL"))
    public void onCraftRequest(ServerboundPlaceRecipePacket packet, CallbackInfo ci) {
        RecipeCooldown.craftingCooldown.put(player.getUUID(), System.currentTimeMillis());
    }
}