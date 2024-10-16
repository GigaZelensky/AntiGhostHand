package com.example.antighosthand;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

public class AntiGhostHand extends JavaPlugin implements Listener {

    private FileConfiguration config;

    @Override
    public void onEnable() {
        // Load the config file
        this.saveDefaultConfig();
        this.config = this.getConfig();
        
        // Register the BlockInteractListener
        getServer().getPluginManager().registerEvents(new BlockInteractListener(this), this);
        
        getLogger().info("AntiGhostHand plugin enabled!");
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll();
        getLogger().info("AntiGhostHand plugin disabled!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("antighosthand")) {
            if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
                // Reload the config
                this.reloadConfig();
                this.config = this.getConfig();
                sender.sendMessage("AntiGhostHand config reloaded!");
                return true;
            }
        }
        return false;
    }

    public FileConfiguration getPluginConfig() {
        return this.config;
    }
}
