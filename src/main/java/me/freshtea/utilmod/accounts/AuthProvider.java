package me.freshtea.utilmod.accounts;

public interface AuthProvider {

    AccountSession login(String username, String password) throws AuthException;

}
