package dev.hevav.randomgive;

import dev.hevav.randomgive.commands.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class RandomGive extends JavaPlugin {
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
    }

    @Override
    public void onDisable(){
        this.saveConfig();
    }
}