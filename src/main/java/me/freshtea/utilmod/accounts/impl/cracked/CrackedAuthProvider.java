package me.freshtea.utilmod.accounts.impl.cracked;

import me.freshtea.utilmod.accounts.AccountSession;
import me.freshtea.utilmod.accounts.AuthProvider;

public class CrackedAuthProvider implements AuthProvider {

    @Override
    public AccountSession login(String username, String password) {
        return new CrackedAccountSession(username);
    }

}
