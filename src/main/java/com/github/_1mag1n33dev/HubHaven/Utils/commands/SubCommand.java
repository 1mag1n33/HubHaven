package com.github._1mag1n33dev.HubHaven.Utils.commands;

import com.github._1mag1n33dev.HubHaven.HubHaven;
import org.bukkit.command.CommandSender;

public interface SubCommand {
    boolean execute(CommandSender sender, String[] args);

    void setPlugin(HubHaven plugin);

    CommandType getType();
}
