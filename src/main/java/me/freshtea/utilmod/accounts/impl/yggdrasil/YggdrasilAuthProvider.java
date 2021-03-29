package me.freshtea.utilmod.accounts.impl.yggdrasil;

import com.mojang.authlib.Agent;
import com.mojang.authlib.UserAuthentication;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import me.freshtea.utilmod.accounts.AccountSession;
import me.freshtea.utilmod.accounts.AuthException;
import me.freshtea.utilmod.accounts.AuthProvider;

import java.net.Proxy;

public class YggdrasilAuthProvider implements AuthProvider {

    private YggdrasilAuthenticationService authService = new YggdrasilAuthenticationService(Proxy.NO_PROXY, "");

    @Override
    public AccountSession login(String username, String password) throws AuthException {
        UserAuthentication auth = authService.createUserAuthentication(Agent.MINECRAFT);

        auth.setUsername(username);
        auth.setPassword(password);

        try {
            auth.logIn();

            return new YggdrasilAccountSession(auth);
        } catch (AuthenticationException e) {
            throw new AuthException(e);
        }
    }

}
