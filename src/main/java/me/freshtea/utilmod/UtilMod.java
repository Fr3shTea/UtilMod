package me.freshtea.utilmod;

import me.freshtea.utilmod.commands.CommandManager;
import me.freshtea.utilmod.commands.command.Toggle;
import me.freshtea.utilmod.eventbus.EventBus;
import me.freshtea.utilmod.modules.ModuleManager;
import me.freshtea.utilmod.modules.module.StrongholdFinder;
import me.freshtea.utilmod.util.named.NameConflictException;
import net.fabricmc.api.ClientModInitializer;

public class UtilMod implements ClientModInitializer {

    private static UtilMod instance;

    private CommandManager commandManager = new CommandManager();
    private ModuleManager moduleManager = new ModuleManager();

    private EventBus eventBus = new EventBus();

    @Override
    public void onInitializeClient() {
        instance = this;

        eventBus.register(commandManager);

        try {
            commandManager.registerCommand(new Toggle());

            moduleManager.registerModule(new StrongholdFinder());
        } catch (NameConflictException e) {
            e.printStackTrace();
        }
    }

    public static UtilMod getInstance() {
        return instance;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }

    public EventBus getEventBus() {
        return eventBus;
    }

}
