package com.github._1mag1n33dev.HubHaven;

import com.github._1mag1n33dev.HubHaven.Config.ConfigManager;
import com.github._1mag1n33dev.HubHaven.Events.EventManager;
import com.github._1mag1n33dev.HubHaven.Commands.CommandManager;
import com.github._1mag1n33dev.HubHaven.Commands.HubCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class HubHaven extends JavaPlugin {
    static private HubHaven instance;

    private DatabaseManager databaseManager;
    private CommandManager commandManager;
    private ConfigManager configManager;
    private EventManager eventManager;

    @Override
    public void onEnable() {
        instance = this;
        this.saveDefaultConfig();

        getLogger().info("==========[ HubHaven ]==========");
        configManager = new ConfigManager(this);
        databaseManager = new DatabaseManager();
        databaseManager.connect();
        databaseManager.createTables();
        eventManager = new EventManager(this);
        commandManager = new CommandManager(this);
        getLogger().info("HubHaven has been enabled!");

        getCommand("hub").setExecutor(new HubCommand(this));
        getCommand("hubhaven").setExecutor(commandManager);

        eventManager.enableListeners();
    }

    @Override
    public void onDisable() {
        getLogger().info("HubHaven has been disabled!");
    }

    public HubHaven getInstance() {
        return instance;
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public EventManager getEventManager() {
        return eventManager;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }
}
