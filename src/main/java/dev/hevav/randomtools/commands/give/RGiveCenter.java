package dev.hevav.randomtools.commands.give;

import dev.hevav.randomtools.RandomTools;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RGiveCenter implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length < 1)
            return false;

        if (strings[0].equals("dis")){
            RandomTools.config.set("rgivecenter", null);
            RandomTools.config.set("rgivecenterloc", null);

            return true;
        }

        if (!(commandSender instanceof Player))
            return false;

        Player player = (Player) commandSender;
        RandomTools.config.set("rgivecenter", Integer.parseInt(strings[0]));
        RandomTools.config.set("rgivecenterloc", player.getLocation());
        return true;
    }
}
