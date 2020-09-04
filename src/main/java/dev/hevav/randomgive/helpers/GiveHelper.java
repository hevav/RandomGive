package dev.hevav.randomgive.helpers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class GiveHelper {
    private static final Random random = new Random();
    public static ItemStack getItemStackByMap(HashMap<String, Integer> map){
        return getItemStack(map.get("id"), map.get("durability").shortValue());
    }

    public static ItemStack getItemStack(int id, short durability){
        ItemStack itemStack = new ItemStack(id);
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

    public static List<ItemStack> getRandomItemStack(List<ItemStack> full, int limit){
        List<ItemStack> randomItemStack = new ArrayList<>();
        for(int i = 0; i<limit; i++){
            randomItemStack.add(full.get(random.nextInt(full.size())));
        }
        return randomItemStack;
    }

    public static ItemStack parseItemStack(String stack){
        if (stack.contains(":")) {
            String[] l  = stack.split(":");
            return GiveHelper.getItemStack(Integer.parseInt(l[0]), Short.parseShort(l[1]));
        }
        else
            return new ItemStack(Integer.parseInt(stack));
    }

    public static HashMap<String, Integer> parseMap(String stack){
        if (stack.contains(":")) {
            String[] l  = stack.split(":");
            HashMap<String, Integer> o = new HashMap<>();
            o.put("id", Integer.valueOf(l[0]));
            o.put("durability", Integer.valueOf(l[1]));
            return o;
        }
        HashMap<String, Integer> o = new HashMap<>();
        o.put("id", Integer.valueOf(stack));
        o.put("durability", 0);
        return o;
    }
}
