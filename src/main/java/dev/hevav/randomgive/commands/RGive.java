package dev.hevav.randomgive.commands;

import dev.hevav.randomgive.helpers.GiveHelper;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RGive implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(strings.length < 3)
            return false;
        try {
            Collection<Player> players = GiveHelper.getPlayerListByString(strings[0]);
            List<ItemStack> toGive = new ArrayList<>();
            for (int i = 2; i < strings.length; i++) {
                toGive.add(GiveHelper.parseItemStack(strings[i]));
            }
            List<ItemStack> randomItemStack = GiveHelper.getRandomItemStack(toGive, Integer.parseInt(strings[1]), false);
            players.forEach(player ->
                randomItemStack.forEach(itemStack ->
                    player.getInventory().addItem(itemStack)
                )
            );
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
