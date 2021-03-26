package me.freshtea.utilmod.util.named;

import java.util.Set;

public class NamedObject {

    private Set<String> names;

    public NamedObject(Set<String> names) {
        if (names.isEmpty())
            throw new IndexOutOfBoundsException("Name set should not be empty.");

        this.names = names;
    }

    public Set<String> getNames() {
        return names;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NamedObject that = (NamedObject) o;

        return names.equals(that.names);
    }

    @Override
    public int hashCode() {
        return names.hashCode();
    }

}
