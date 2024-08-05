package com.github._1mag1n33dev.HubHaven.Events;

import com.github._1mag1n33dev.HubHaven.HubHaven;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

import java.util.HashMap;
import java.util.Map;

public class EventManager {
    private final Map<String, Listener> listeners = new HashMap<>();
    private final HubHaven plugin;

    public EventManager(HubHaven plugin) {
        this.plugin = plugin;
    }

    public void enableListeners() {
        registerListener("MenuListener", new MenuListener(plugin));
        registerListener("HotbarEventListener", new HotbarListener(plugin));
        registerListener("PlayerJoin", new PlayerJoinListener(plugin));
        registerListener("itemUseListener", new ItemUseListener(plugin));
        registerListener("NPCInteractionListener", new NPCInteractionListener(plugin));
    }

    public void registerListener(String name, Listener listener) {
        plugin.getServer().getPluginManager().registerEvents(listener, plugin);
        listeners.put(name, listener);
    }

    public void unregisterListener(String name) {
        Listener listener = listeners.remove(name);
        if (listener != null) {
            HandlerList.unregisterAll(listener);
        }
    }

    public void unregisterAllListeners() {
        for (Listener listener : listeners.values()) {
            HandlerList.unregisterAll(listener);
        }
        listeners.clear();
    }

    public Listener getListener(String name) {
        return listeners.get(name);
    }

    public Map<String, Listener> getListeners() {
        return new HashMap<>(listeners);
    }
}
