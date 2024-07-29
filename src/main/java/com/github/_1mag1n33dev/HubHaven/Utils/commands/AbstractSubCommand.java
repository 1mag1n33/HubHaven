package com.github._1mag1n33dev.HubHaven.Utils.commands;

import com.github._1mag1n33dev.HubHaven.HubHaven;

public abstract class AbstractSubCommand implements SubCommand {
    protected HubHaven plugin;

    @Override
    public void setPlugin(HubHaven plugin) {
        this.plugin = plugin;
    }

    protected HubHaven getPlugin() {
        return this.plugin;
    }
}
