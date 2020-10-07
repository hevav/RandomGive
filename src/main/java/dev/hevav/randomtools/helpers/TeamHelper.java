package dev.hevav.randomtools.helpers;

import dev.hevav.randomtools.RandomTools;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TeamHelper {
    private static List<List<String>> teamsCache;

    public static boolean createTeams(int limit){
        if (limit < 1)
            return false;

        List<Player> playerList = new ArrayList<>(Bukkit.getOnlinePlayers());
        if (limit > playerList.size())
            return false;

        saveTeams(getTeams(playerList, limit));
        return true;
    }

    public static List<List<String>> getTeams(List<Player> all, int limit){
        int playersCount = all.size();
        int teamsCount = (playersCount + limit - 1) / limit;

        List<List<String>> teams = new ArrayList<>();
        for (int i = 0; i < teamsCount; ++i){
            teams.add(new ArrayList<>());
        }

        Collections.shuffle(all);
        for (int i = 0; i < playersCount; ++i){
            teams.get(i % teamsCount).add(all.get(i).getName());
        }

        return teams;
    }

    public static void saveTeams(List<List<String>> teams){
        RandomTools.config.set("teams", teams);
        updateCache();
    }

    public static void updateCache(){
        if (RandomTools.config.contains("teams"))
            teamsCache = (List<List<String>>) RandomTools.config.get("teams");
    }

    public static void removeTeams(){
        teamsCache = null;
        RandomTools.config.set("teams", null);
    }

    public static List<List<String>> applyOperator(String operator){
        switch (operator.charAt(0)){
            case '*':
                return teamsCache;
            case '%':
                String[] nums = operator.substring(1).split("\\+");
                int everyNum = Integer.parseInt(nums[0]);
                int startNum = Integer.parseInt(nums[1]);

                List<List<String>> teams = new ArrayList<>();

                for (int i = 0; i < teamsCache.size() / everyNum; ++i){
                    teams.add(teamsCache.get((i * everyNum) + startNum - 1));
                }

                return teams;
            default:
                int num = Integer.parseInt(operator) - 1;
                return Collections.singletonList(teamsCache.get(num));
        }
    }

    public static List<Player> getValidTeamMembers(List<String> team){
        List<Player> teams = new ArrayList<>();

        for (String memberName : team){
            Player player = Bukkit.getPlayerExact(memberName);
            if (player != null)
                teams.add(player);
        }
        return teams;
    }
}
