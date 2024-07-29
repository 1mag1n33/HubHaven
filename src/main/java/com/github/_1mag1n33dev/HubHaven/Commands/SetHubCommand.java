package com.github._1mag1n33dev.HubHaven.Commands;

import com.github._1mag1n33dev.HubHaven.Utils.commands.AbstractSubCommand;
import com.github._1mag1n33dev.HubHaven.Utils.commands.CommandType;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetHubCommand extends AbstractSubCommand {

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        Player player = (Player) sender;
        Location hubLocation = player.getLocation();
        getPlugin().getDatabaseManager().setHubLocation(
                hubLocation.getWorld().getName(),
                hubLocation.getX(),
                hubLocation.getY(),
                hubLocation.getZ(),
                hubLocation.getYaw(),
                hubLocation.getPitch()
        );

        player.sendMessage("Hub location set!");
        return true;
    }

    @Override
    public CommandType getType() {
        return CommandType.ADMIN;
    }
}
