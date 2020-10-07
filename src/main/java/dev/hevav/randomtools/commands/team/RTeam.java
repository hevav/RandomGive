package dev.hevav.randomtools.commands.team;

import dev.hevav.randomtools.helpers.TeamHelper;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class RTeam implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length < 2)
            return false;

        if (!(commandSender instanceof Player))
            return false;

        Player player = (Player) commandSender;
        List<List<String>> teams = TeamHelper.applyOperator(strings[0]);

        switch (strings[1]){
            case "tp":
                for (List<String> team : teams) {
                    List<Player> players = TeamHelper.getValidTeamMembers(team);
                    players.forEach(p -> p.teleport(player));
                }
                return true;
            default:
                return false;
        }
    }
}
