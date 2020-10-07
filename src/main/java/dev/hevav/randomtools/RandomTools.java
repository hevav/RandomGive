package dev.hevav.randomtools;

import dev.hevav.randomtools.commands.give.*;
import dev.hevav.randomtools.helpers.TeamHelper;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class RandomTools extends JavaPlugin {
    public static FileConfiguration config;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        config = this.getConfig();

        this.getCommand("rgive").setExecutor(new RGive());
        this.getCommand("rkit").setExecutor(new RKit());
        this.getCommand("rkitloop").setExecutor(new RKitLoop(this));
        this.getCommand("rkitadd").setExecutor(new RKitAdd());
        this.getCommand("rkitremove").setExecutor(new RKitRemove());

        TeamHelper.updateCache();
    }

    @Override
    public void onDisable(){
        this.saveConfig();
    }
}