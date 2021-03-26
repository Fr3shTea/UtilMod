package me.freshtea.utilmod.eventbus.events;

import me.freshtea.utilmod.eventbus.CancellableEvent;

public class ChatEvent extends CancellableEvent {

    private String text;

    public ChatEvent(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
