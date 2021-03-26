package me.freshtea.utilmod.commands;

import me.freshtea.utilmod.util.named.NamedObject;

import java.util.Set;

public abstract class Command extends NamedObject {

    public Command(Set<String> names) {
        super(names);
    }

    public abstract void execute(String[] args);

}
