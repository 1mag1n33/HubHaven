package com.github._1mag1n33dev.HubHaven;

import com.github._1mag1n33dev.HubHaven.Commands.CommandManager;
import com.github._1mag1n33dev.HubHaven.Commands.HubCommand;
import com.github._1mag1n33dev.HubHaven.Config.ConfigManager;
import com.github._1mag1n33dev.HubHaven.Events.EventManager;
import com.github._1mag1n33dev.HubHaven.NMS.common.PacketManager;
import com.github._1mag1n33dev.HubHaven.NMS.common.PacketManagerFactory;
import com.github._1mag1n33dev.HubHaven.NMS.common.npc.NPCManager;
import com.github._1mag1n33dev.HubHaven.NMS.common.npc.NPCRegistry;
import com.github._1mag1n33dev.HubHaven.Utils.HotbarUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class HubHaven extends JavaPlugin {
    private static HubHaven instance;

    private DatabaseManager databaseManager;
    private CommandManager commandManager;
    private ConfigManager configManager;
    private EventManager eventManager;
    private HotbarUtils hotbarUtils;
    private PacketManager packetManager;
    private NPCRegistry npcRegistry;
    private NPCManager npcManager;

    @Override
    public void onEnable() {
        instance = this;
        this.saveDefaultConfig();

        getLogger().info("==========[ HubHaven ]==========");
        configManager = new ConfigManager(this);
        databaseManager = new DatabaseManager();
        databaseManager.connect();
        databaseManager.createTables();
        String serverVersion = getServerVersion();
        npcRegistry = new NPCRegistry();
        PacketManagerFactory factory = new PacketManagerFactory(serverVersion);
        packetManager = factory.createPacketManager(this);
        npcManager = packetManager.getNPCManager();

        eventManager = new EventManager(this);
        commandManager = new CommandManager(this);
        hotbarUtils = new HotbarUtils(this);
        getLogger().info("HubHaven has been enabled!");

        getCommand("hub").setExecutor(new HubCommand(this));
        getCommand("hubhaven").setExecutor(commandManager);

        eventManager.enableListeners();

        // Debugging information
        getLogger().info("ConfigManager initialized: " + (configManager != null));
        getLogger().info("DatabaseManager initialized: " + (databaseManager != null));
        getLogger().info("EventManager initialized: " + (eventManager != null));
        getLogger().info("CommandManager initialized: " + (commandManager != null));
        getLogger().info("HotbarUtils initialized: " + (hotbarUtils != null));
    }

    @Override
    public void onDisable() {
        getLogger().info("HubHaven has been disabled!");
    }



    private String getServerVersion() {
        String packageName = Bukkit.getServer().getClass().getPackage().getName();
        return packageName.substring(packageName.lastIndexOf('.') + 1);
    }

    public static HubHaven getInstance() {
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

    public HotbarUtils getHotbarUtils() {
        return hotbarUtils;
    }

    public PacketManager getPacketManager() {
        return packetManager;
    }

    public NPCManager getNPCManager() {
        return npcManager;
    }

    public NPCRegistry getNPCRegistry() {
        return npcRegistry;
    }
}
