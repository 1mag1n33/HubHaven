package com.github._1mag1n33dev.HubHaven.Utils.Events;

import com.github._1mag1n33dev.HubHaven.Config.ConfigManager;
import com.github._1mag1n33dev.HubHaven.HubHaven;
import org.bukkit.event.Listener;

public abstract class AbstractEvent implements Event, Listener {
    protected HubHaven plugin;

    @Override
    public void setPlugin(HubHaven plugin) {
        this.plugin = plugin;
    }

    protected HubHaven getPlugin() {
        return this.plugin;
    }


}
