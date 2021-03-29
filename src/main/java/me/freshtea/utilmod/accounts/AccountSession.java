package me.freshtea.utilmod.accounts;

import net.minecraft.client.util.Session;

public abstract class AccountSession {

    private String username;

    private String accessToken;
    private String uuid;

    private AuthType type;

    public AccountSession(String username, String accessToken, String uuid, AuthType type) {
        this.username = username;
        this.accessToken = accessToken;
        this.uuid = uuid;
        this.type = type;
    }

    public abstract void refresh();
    public abstract void logout();

    public String getUsername() {
        return username;
    }

    public Session asMinecraftSession() {
        return new Session(username, uuid, accessToken, "mojang");
    }

}
