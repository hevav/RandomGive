package dev.hevav.randomtools.commands.give;

import dev.hevav.randomtools.RandomTools;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class RKitRemove implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(strings.length != 1)
            return false;
        RandomTools.config.set(String.format("kits.%s", strings[0]), null);
        return true;
    }
}
