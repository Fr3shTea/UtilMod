package me.freshtea.utilmod.commands.command;

import com.google.common.collect.Sets;
import me.freshtea.utilmod.UtilMod;
import me.freshtea.utilmod.commands.Command;
import me.freshtea.utilmod.modules.Module;
import me.freshtea.utilmod.util.MsgUtil;

public class Toggle extends Command {

    public Toggle() {
        super(Sets.newHashSet("toggle", "switch"));
    }

    @Override
    public void execute(String[] args) {
        Module module = UtilMod.getInstance().getModuleManager().findModule(args[1]);

        if (module != null) {
            module.toggle();
        } else {
            MsgUtil.sendMessage("Module not found.");
        }
    }

}
