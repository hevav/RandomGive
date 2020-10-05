package dev.hevav.randomgive.commands;

import dev.hevav.randomgive.helpers.GiveHelper;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class RKit implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(strings.length != 3)
            return false;
        return GiveHelper.giveAKit(strings[0], strings[1], Integer.parseInt(strings[2]));
    }
}
