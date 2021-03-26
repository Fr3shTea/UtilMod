package me.freshtea.utilmod.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.LiteralText;

public class MsgUtil {

    public static void sendMessage(String msg) {
        MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(new LiteralText("[UtilMod] " + msg));
    }

}
