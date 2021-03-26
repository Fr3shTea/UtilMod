package me.freshtea.utilmod.modules;

import me.freshtea.utilmod.UtilMod;
import me.freshtea.utilmod.util.named.NamedObject;

import java.util.Set;

public abstract class Module extends NamedObject {

    private int keyBind;

    private boolean active;

    public Module(Set<String> aliases, int keyBind, boolean active) {
        super(aliases);

        this.keyBind = keyBind;
        this.active = active;
    }

    public abstract void onEnable();
    public abstract void onDisable();

    public int getKeyBind() {
        return keyBind;
    }

    public boolean isActive() {
        return active;
    }

    public void setKeyBind(int keyBind) {
        this.keyBind = keyBind;
    }

    private void setActive(boolean active) {
        this.active = active;

        UtilMod.getInstance().getEventBus().setActive(this, active);

        if (active) {
            onEnable();
        } else {
            onDisable();
        }
    }

    public void toggle() {
        setActive(!active);
    }

}
