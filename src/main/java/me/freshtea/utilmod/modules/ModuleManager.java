package me.freshtea.utilmod.modules;

import me.freshtea.utilmod.UtilMod;
import me.freshtea.utilmod.util.named.NameConflictException;
import me.freshtea.utilmod.util.named.NamedObjectRegistry;
import me.freshtea.utilmod.util.named.TreeNamedObjectRegistry;

public class ModuleManager {

    private NamedObjectRegistry<Module> moduleRegistry = new TreeNamedObjectRegistry<>();

    public void registerModule(Module module) throws NameConflictException {
        UtilMod.getInstance().getEventBus().register(module);

        moduleRegistry.register(module);
    }

    public Module findModule(String name) {
        return moduleRegistry.find(name);
    }

}
