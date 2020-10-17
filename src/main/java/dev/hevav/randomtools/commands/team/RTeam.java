package dev.hevav.randomtools.commands.team;

import dev.hevav.randomtools.helpers.TeamHelper;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

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
            case "toMc":
                for (List<String> team : teams) {
                    Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
                    Team mcTeam = scoreboard.getTeam("hrt" + teams.indexOf(team));

                    if (mcTeam == null)
                        mcTeam = scoreboard.registerNewTeam(String.valueOf(teams.indexOf(team)));

                    mcTeam.getEntries().forEach(mcTeam::removeEntry);
                    team.forEach(mcTeam::addEntry);
                }
                return true;
            default:
                return false;
        }
    }
}
