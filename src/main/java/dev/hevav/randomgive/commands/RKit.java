package dev.hevav.randomgive.commands;

import dev.hevav.randomgive.RandomGive;
import dev.hevav.randomgive.helpers.GiveHelper;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class RKit implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(strings.length != 3)
            return false;
        try {
            Collection<Player> players = GiveHelper.getPlayerListByString(strings[0]);
            List<HashMap<String, Integer>> maps = (List<HashMap<String, Integer>>) RandomGive.config.get(String.format("kits.%s", strings[1]));
            List<ItemStack> toGive = new ArrayList<>();
            maps.forEach(map ->
                    toGive.add(GiveHelper.getItemStackByMap(map))
            );
            List<ItemStack> randomItemStack = GiveHelper.getRandomItemStack(toGive, Integer.parseInt(strings[2]));
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
