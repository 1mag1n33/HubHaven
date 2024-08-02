package com.github._1mag1n33dev.HubHaven.Utils.Events;

import com.github._1mag1n33dev.HubHaven.HubHaven;
import org.bukkit.event.Listener;

public abstract class AbstractEvent implements Listener {
    protected HubHaven plugin;
    protected String name;

    protected HubHaven getPlugin() {
        return this.plugin;
    }

    protected String getName() { return this.name; }

    public void setName (String name) {this.name = name;}

    public void setPlugin(HubHaven plugin) {
        this.plugin = plugin;
    }


}
