package me.freshtea.utilmod.util.named;

public interface NamedObjectRegistry<T extends NamedObject> {

    void register(T obj) throws NameConflictException;

    T find(String name);

}
