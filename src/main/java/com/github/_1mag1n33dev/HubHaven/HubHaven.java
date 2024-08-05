package com.github._1mag1n33dev.HubHaven;

import com.github._1mag1n33dev.HubHaven.Commands.CommandManager;
import com.github._1mag1n33dev.HubHaven.Commands.HubCommand;
import com.github._1mag1n33dev.HubHaven.Config.ConfigManager;
import com.github._1mag1n33dev.HubHaven.Events.EventManager;
import com.github._1mag1n33dev.HubHaven.NMS.V1_8.entity.EntityPlayerV1_8;
import com.github._1mag1n33dev.HubHavenApi.network.PacketManager;
import com.github._1mag1n33dev.HubHaven.NMS.common.VersionFactory;
import com.github._1mag1n33dev.HubHaven.Utils.HotbarUtils;
import com.github._1mag1n33dev.HubHavenApi.npc.EntityRegistry;
import com.github._1mag1n33dev.HubHavenApi.npc.NPCManager;
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
    private EntityRegistry entityRegistry;
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
        VersionFactory factory = new VersionFactory(serverVersion);
        packetManager = factory.createPacketManager(this);
        RegisterCustomEntitys();
        npcManager = factory.createNPCManager(this);

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

    private void RegisterCustomEntitys() {
        EntityRegistry.registerEntity("player", EntityPlayerV1_8.class);
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

    public NPCManager getNPCManager() { return npcManager; }

    public EntityRegistry getEntityRegistry() {
        return entityRegistry;
    }
}
