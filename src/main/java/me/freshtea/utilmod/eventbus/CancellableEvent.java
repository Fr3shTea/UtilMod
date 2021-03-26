package me.freshtea.utilmod.eventbus;

public abstract class CancellableEvent implements Event {

    private boolean cancelled = false;

    public void cancel() {
        cancelled = true;
    }

    public boolean isCancelled() {
        return cancelled;
    }

}
