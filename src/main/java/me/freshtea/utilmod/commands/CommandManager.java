package me.freshtea.utilmod.commands;

import me.freshtea.utilmod.eventbus.Listen;
import me.freshtea.utilmod.eventbus.events.ChatEvent;
import me.freshtea.utilmod.util.MsgUtil;
import me.freshtea.utilmod.util.named.NameConflictException;
import me.freshtea.utilmod.util.named.NamedObjectRegistry;
import me.freshtea.utilmod.util.named.TreeNamedObjectRegistry;

public class CommandManager {

    private static final String PREFIX = "#";

    private NamedObjectRegistry<Command> commandRegistry = new TreeNamedObjectRegistry<>();

    public void registerCommand(Command command) throws NameConflictException {
        commandRegistry.register(command);
    }

    private boolean tryExecute(String msg) {
        // TODO: Code a normal parser and handle syntax errors adequately (not just ignore them).

        if (msg.startsWith(PREFIX)) {
            String[] commandArgs = msg.substring(PREFIX.length()).split("\\s+");

            if (commandArgs.length > 0) {
                Command cmd = commandRegistry.find(commandArgs[0]);

                if (cmd != null) {
                    cmd.execute(commandArgs);
                } else {
                    MsgUtil.sendMessage("Command not found!");
                }
            }

            return true;
        }

        return false;
    }

    @Listen
    public void onChatMessage(ChatEvent event) {
        if (tryExecute(event.getText()))
            event.cancel();
    }

}
