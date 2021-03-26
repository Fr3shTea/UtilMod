package me.freshtea.utilmod.eventbus;

import java.lang.invoke.MethodHandle;

public class Subscription {

    private int priority;

    private Object object;
    private MethodHandle handle;

    private volatile boolean active = true;

    public Subscription(int priority, Object object, MethodHandle handle) {
        this.priority = priority;
        this.object = object;
        this.handle = handle.bindTo(object);
    }

    public int getPriority() {
        return priority;
    }

    public Object getObject() {
        return object;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void tryInvoke(Event event) throws Throwable {
        if (active)
            handle.invoke(event);
    }

}
