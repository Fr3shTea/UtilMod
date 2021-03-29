package me.freshtea.utilmod.accounts;

import me.freshtea.utilmod.accounts.impl.cracked.CrackedAuthProvider;
import me.freshtea.utilmod.accounts.impl.yggdrasil.YggdrasilAuthProvider;

public enum AuthType {

    YGGDRASIL(new YggdrasilAuthProvider()),
    CRACKED(new CrackedAuthProvider());

    private AuthProvider provider;

    AuthType(AuthProvider provider) {
        this.provider = provider;
    }

    public AuthProvider getProvider() {
        return provider;
    }

}
