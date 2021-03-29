package me.freshtea.utilmod.commands.command;

import com.google.common.collect.Sets;
import me.freshtea.utilmod.UtilMod;
import me.freshtea.utilmod.accounts.AccountManager;
import me.freshtea.utilmod.accounts.AccountSession;
import me.freshtea.utilmod.accounts.AuthException;
import me.freshtea.utilmod.accounts.AuthType;
import me.freshtea.utilmod.commands.Command;
import me.freshtea.utilmod.util.MsgUtil;

public class Login extends Command {

    public Login() {
        super(Sets.newHashSet("login"));
    }

    @Override
    public void execute(String[] args) {
        AccountManager manager = UtilMod.getInstance().getAccountManager();

        switch (args[1].toLowerCase()) {
            case "create": {
                try {
                    manager.createSession(AuthType.valueOf(args[2].toUpperCase()), args[3], args[4]);
                } catch (AuthException e) {
                    MsgUtil.sendMessage("Unable to login.");
                }

                break;
            }
            case "login": {
                AccountSession session = manager.getAccountByName(args[2]);

                if (session == null) {
                    MsgUtil.sendMessage("Unable to login.");
                } else {
                    manager.login(session);
                }

                break;
            }
            case "list": {
                MsgUtil.sendMessage("Sessions:");

                for (AccountSession session : manager.getAccounts())
                    MsgUtil.sendMessage(session.getUsername());
            }
        }
    }

}
