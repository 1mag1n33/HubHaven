package com.github._1mag1n33dev.HubHaven.Commands;

import com.github._1mag1n33dev.HubHaven.Utils.commands.AbstractSubCommand;
import com.github._1mag1n33dev.HubHaven.Utils.commands.CommandType;
import org.bukkit.command.CommandSender;

public class ClearHubCommand extends AbstractSubCommand {

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        plugin.getDatabaseManager().clearHubLocation();
        sender.sendMessage("Hub location cleared!");
        return true;
    }

    @Override
    public CommandType getType() {
        return CommandType.ADMIN;
    }

}
