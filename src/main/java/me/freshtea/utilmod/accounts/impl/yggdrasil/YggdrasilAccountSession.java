package me.freshtea.utilmod.accounts.impl.yggdrasil;

import com.mojang.authlib.UserAuthentication;
import me.freshtea.utilmod.accounts.AccountSession;
import me.freshtea.utilmod.accounts.AuthType;

public class YggdrasilAccountSession extends AccountSession {

    private UserAuthentication auth;

    public YggdrasilAccountSession(UserAuthentication auth) {
        super(auth.getSelectedProfile().getName(), auth.getAuthenticatedToken(), auth.getUserID(), AuthType.YGGDRASIL);

        this.auth = auth;
    }

    @Override
    public void refresh() {}

    @Override
    public void logout() {
        auth.logOut();
    }

}
