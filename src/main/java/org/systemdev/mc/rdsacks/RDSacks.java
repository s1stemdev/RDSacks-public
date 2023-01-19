package org.systemdev.mc.rdsacks;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.systemdev.mc.rdsacks.Commands.GiveCommand;
import org.systemdev.mc.rdsacks.Commands.ReloadCommand;
import org.systemdev.mc.rdsacks.Events.OpenInventory;
import org.systemdev.mc.rdsacks.Events.PickupEvent;

import java.util.logging.Logger;

public final class RDSacks extends JavaPlugin {

    public final Logger log = getLogger();
    private static RDSacks instance;

    @Override
    public void onEnable() {
        instance = this;
        log.info("RDSacks has been enabled successful!");
        log.info("Authors: systemdev_");
        saveDefaultConfig();
        getCommand("sacks-give").setExecutor(new GiveCommand());
        getCommand("sacks-reload").setExecutor(new ReloadCommand());
        Bukkit.getPluginManager().registerEvents(new PickupEvent(), this);
        Bukkit.getPluginManager().registerEvents(new OpenInventory(), this);
    }

    @Override
    public void onDisable() {
        log.info("RDSacks has been disabled successful!");
    }

    public static RDSacks getInstance() {
        return instance;
    }

}
