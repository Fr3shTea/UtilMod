package me.freshtea.utilmod.util;

import net.minecraft.client.util.Session;

public interface SessionContainer {

    Session getSession();
    void setSession(Session session);

}
