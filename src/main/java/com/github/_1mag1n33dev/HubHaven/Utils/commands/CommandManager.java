package com.github._1mag1n33dev.HubHaven.Utils.commands;

import com.github._1mag1n33dev.HubHaven.Commands.HubCommand;
import com.github._1mag1n33dev.HubHaven.Commands.SetHubCommand;
import com.github._1mag1n33dev.HubHaven.HubHaven;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class CommandManager implements CommandExecutor {
    private final Map<String, SubCommand> commands = new HashMap<>();
    private final HubHaven plugin;

    public CommandManager(HubHaven plugin) {
        this.plugin = plugin;

        registerCommand("hub", new HubCommand(plugin));
        registerCommand("sethub", new SetHubCommand());

    }

    private void registerCommand(String name, SubCommand command) {
        command.setPlugin(plugin);
        commands.put(name, command);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage("Please specify a sub-command!");
            return false;
        }

        SubCommand subCommand = commands.get(args[0].toLowerCase());
        if (subCommand == null) {
            sender.sendMessage("Unknow command!");
            return false;
        }

        CommandType type = subCommand.getType();
        if (type == CommandType.PLAYER && !sender.hasPermission("hubhaven.use")) {
            sender.sendMessage("You do not have permission to use this command.");
            return true;
        } else if (type == CommandType.ADMIN && !sender.hasPermission("hubhaven.admin")) {
            sender.sendMessage("You do not have permission to use this command.");
            return true;
        }

        return subCommand.execute(sender, args);
    }
}
