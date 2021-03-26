package me.freshtea.utilmod.util.named;

public class NameConflictException extends Exception {

    public NameConflictException() {
        super("Name conflict happened while registering named objects.");
    }

}
