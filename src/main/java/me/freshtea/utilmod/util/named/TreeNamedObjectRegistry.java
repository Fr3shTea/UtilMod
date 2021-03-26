package me.freshtea.utilmod.util.named;

import java.util.Map;
import java.util.TreeMap;

public class TreeNamedObjectRegistry<T extends NamedObject> implements NamedObjectRegistry<T> {

    private Map<String, T> objSet = new TreeMap<>();

    @Override
    public void register(T obj) throws NameConflictException {
        for (String name : obj.getNames())
            if (objSet.putIfAbsent(name.toLowerCase(), obj) != null)
                throw new NameConflictException();
    }

    @Override
    public T find(String name) {
        return objSet.get(name.toLowerCase());
    }

}
