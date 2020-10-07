package dev.hevav.randomtools.commands.team;

import dev.hevav.randomtools.helpers.TeamHelper;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class RTeamAdd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length < 1)
            return false;
        return TeamHelper.createTeams(Integer.parseInt(strings[0]));
    }
}
