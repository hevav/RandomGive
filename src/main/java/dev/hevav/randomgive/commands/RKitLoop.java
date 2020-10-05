package dev.hevav.randomgive.commands;

import dev.hevav.randomgive.helpers.GiveHelper;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class RKitLoop implements CommandExecutor {
    Plugin plugin;

    public RKitLoop (Plugin plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 1 && strings[0].equals("dis")){
            Bukkit.getScheduler().cancelTasks(plugin);
            return true;
        }

        if (strings.length != 4)
            return false;

        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, ()-> GiveHelper.giveAKit(strings[0], strings[1], Integer.parseInt(strings[2])), 0, Integer.parseInt(strings[3]) * 20);
        return true;
    }
}
