package me.freshtea.utilmod.accounts.impl.cracked;

import me.freshtea.utilmod.accounts.AccountSession;
import me.freshtea.utilmod.accounts.AuthType;

public class CrackedAccountSession extends AccountSession {

    public CrackedAccountSession(String username) {
        super(username, "", "", AuthType.CRACKED);
    }

    @Override
    public void refresh() {}

    @Override
    public void logout() {}

}
