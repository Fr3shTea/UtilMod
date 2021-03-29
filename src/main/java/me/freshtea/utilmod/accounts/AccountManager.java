package me.freshtea.utilmod.accounts;

import me.freshtea.utilmod.util.SessionContainer;
import net.minecraft.client.MinecraftClient;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AccountManager {

    private Map<String, AccountSession> accounts = new HashMap<>();

    public void login(AccountSession session) {
        ((SessionContainer) MinecraftClient.getInstance()).setSession(session.asMinecraftSession());
    }

    public AccountSession createSession(AuthType type, String username, String password) throws AuthException {
        AccountSession session = type.getProvider().login(username, password);

        accounts.put(session.getUsername(), session);

        return session;
    }

    public AccountSession getAccountByName(String name) {
        return accounts.get(name);
    }

    public Collection<AccountSession> getAccounts() {
        return accounts.values();
    }

}
