package me.freshtea.utilmod.mixins;

import me.freshtea.utilmod.UtilMod;
import me.freshtea.utilmod.eventbus.events.ChatEvent;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {

    @Inject(method = "sendChatMessage(Ljava/lang/String;)V", at = @At("HEAD"), cancellable = true)
    public void onSendChatMessage(String msg, CallbackInfo info) {
        ChatEvent event = new ChatEvent(msg);

        UtilMod.getInstance().getEventBus().post(event);

        if (event.isCancelled())
            info.cancel();
    }

}
