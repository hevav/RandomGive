package dev.hevav.randomtools.commands.team;

import dev.hevav.randomtools.helpers.TeamHelper;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class RTeamRemove implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        TeamHelper.removeTeams();
        return true;
    }
}
