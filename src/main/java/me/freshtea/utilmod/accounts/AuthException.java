package me.freshtea.utilmod.accounts;

public class AuthException extends Exception {

    public AuthException(Throwable cause) {
        super("Unable to log in.", cause);
    }

}
