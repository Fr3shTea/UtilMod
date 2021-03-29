package me.freshtea.utilmod;

import me.freshtea.utilmod.accounts.AccountManager;
import me.freshtea.utilmod.commands.CommandManager;
import me.freshtea.utilmod.commands.command.Login;
import me.freshtea.utilmod.commands.command.Toggle;
import me.freshtea.utilmod.eventbus.EventBus;
import me.freshtea.utilmod.modules.ModuleManager;
import me.freshtea.utilmod.modules.module.StrongholdFinder;
import me.freshtea.utilmod.util.named.NameConflictException;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.minecraft.client.MinecraftClient;

public class UtilMod implements ClientModInitializer {

    private static UtilMod instance;

    private CommandManager commandManager = new CommandManager();
    private ModuleManager moduleManager = new ModuleManager();

    private EventBus eventBus = new EventBus();

    private AccountManager accountManager = new AccountManager();

    @Override
    public void onInitializeClient() {
        instance = this;

        ClientLifecycleEvents.CLIENT_STOPPING.register(this::onClientStopping);

        eventBus.register(commandManager);

        try {
            commandManager.registerCommand(new Toggle());
            commandManager.registerCommand(new Login());

            moduleManager.registerModule(new StrongholdFinder());
        } catch (NameConflictException e) {
            e.printStackTrace();
        }
    }

    public void onClientStopping(MinecraftClient client) {
        // Client shutdown.
    }

    public static UtilMod getInstance() {
        return instance;
    }

    public AccountManager getAccountManager() {
        return accountManager;
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
