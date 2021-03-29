package me.freshtea.utilmod.mixins;

import me.freshtea.utilmod.util.SessionContainer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Session;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin implements SessionContainer {

    @Shadow
    private Session session;

    @Shadow
    public abstract Session getSession();

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

}
