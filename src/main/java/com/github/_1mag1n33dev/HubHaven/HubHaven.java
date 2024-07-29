package com.github._1mag1n33dev.HubHaven;

import com.github._1mag1n33dev.HubHaven.Utils.commands.CommandManager;
import com.github._1mag1n33dev.HubHaven.Commands.HubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class HubHaven extends JavaPlugin {
    private DatabaseManager databaseManager;
    private CommandManager commandManager;

    @Override
    public void onEnable() {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }
        getLogger().info("HubHaven has been enabled!");
        databaseManager = new DatabaseManager();
        databaseManager.connect();
        databaseManager.createTables();
        commandManager = new CommandManager(this);

        getCommand("hub").setExecutor(new HubCommand(this));
        getCommand("hubhaven").setExecutor(commandManager);
    }

    @Override
    public void onDisable() {
        getLogger().info("HubHaven has been disabled!");
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }
}
