package com.github._1mag1n33dev.HubHaven.Commands;


import com.github._1mag1n33dev.HubHaven.HubHaven;
import com.github._1mag1n33dev.HubHaven.Utils.commands.AbstractSubCommand;
import com.github._1mag1n33dev.HubHaven.Utils.commands.CommandType;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HubCommand extends AbstractSubCommand implements CommandExecutor {

    public HubCommand(HubHaven plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        Location hubLocation = plugin.getDatabaseManager().getHubLocation();

        if (hubLocation == null) {
            player.sendMessage("Hub location is not set.");
            return true;
        }

        player.teleport(hubLocation);
        player.playSound(player.getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
        player.sendMessage("Teleported to the hub!");
        return true;
    }

    @Override
    public CommandType getType() {
        return CommandType.PLAYER;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return execute(sender, args);
    }
}
