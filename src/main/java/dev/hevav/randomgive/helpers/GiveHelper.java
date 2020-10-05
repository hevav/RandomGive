package dev.hevav.randomgive.helpers;

import dev.hevav.randomgive.RandomGive;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class GiveHelper {
    private static final Random random = new Random();
    public static ItemStack getItemStackByMap(HashMap<String, String> map){
        return getItemStack(map.get("id"), Short.parseShort(map.get("durability")));
    }

    public static ItemStack getItemStack(String id, short durability){
        ItemStack itemStack = new ItemStack(Material.valueOf(id.toUpperCase()));
        itemStack.setDurability(durability);
        return itemStack;
    }

    public static Collection<Player> getPlayerListByString(String str){
        if(str.equals("@a"))
            return (Collection<Player>) Bukkit.getOnlinePlayers();
        if(str.equals("@p")){
            Collection<? extends Player> collection = Bukkit.getOnlinePlayers();
            return Collections.singleton(new ArrayList<>(collection).get(random.nextInt(collection.size() - 1)));
        }
        return Collections.singleton(Bukkit.getPlayerExact(str));
    }

    public static List<ItemStack> getRandomItemStack(List<ItemStack> full, int limit, boolean invert){
        List<ItemStack> randomItemStack = new ArrayList<>();
        for(int i = 0; i<limit; i++){
            ItemStack itemStack = full.get(random.nextInt(full.size()));
            if (invert){
                Material[] all = Material.values();
                Material material;
                while (true){
                    material = all[random.nextInt(all.length - 1)];
                    for (ItemStack item : full)
                        if (item.getType() == material)
                            continue;
                    break;
                }
                itemStack = new ItemStack(material);
            }
            randomItemStack.add(itemStack);
        }
        return randomItemStack;
    }

    public static ItemStack parseItemStack(String stack){
        HashMap<String, String> parseMap = parseMap(stack);

        return new ItemStack(getItemStack(parseMap.get("id"), Short.parseShort(parseMap.get("durability"))));
    }

    public static HashMap<String, String> parseMap(String stack){
        HashMap<String, String> o = new HashMap<>();
        String id = stack;
        String durability = "0";
        boolean isNeg = stack.startsWith("-");

        if (stack.contains(":")) {
            String[] l  = stack.split(":");
            id = l[0];
            durability = l[1];
        }

        if (isNeg)
            id = id.substring(1);

        o.put("id", id);
        o.put("durability", durability);
        o.put("isNeg", isNeg? "true" : "");
        return o;
    }

    public static boolean giveAKit(String playersString, String kitName, int limit){
        try {
            Collection<Player> players = GiveHelper.getPlayerListByString(playersString);
            List<HashMap<String, String>> maps = (List<HashMap<String, String>>) RandomGive.config.get(String.format("kits.%s", kitName));
            boolean invert = maps.get(0).get("isNeg").equals("true");
            List<ItemStack> toGive = new ArrayList<>();
            maps.forEach(map ->
                    toGive.add(GiveHelper.getItemStackByMap(map))
            );

            players.forEach(player -> {
                        List<ItemStack> randomItemStack = GiveHelper.getRandomItemStack(toGive, limit, invert);
                        randomItemStack.forEach(itemStack ->
                                player.getInventory().addItem(itemStack)
                        );
                    }
            );
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
