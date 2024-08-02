package com.github._1mag1n33dev.HubHaven.Commands.npc;

import com.github._1mag1n33dev.HubHaven.NMS.common.npc.NPCManager;
import com.github._1mag1n33dev.HubHaven.Utils.commands.AbstractSubCommand;
import com.github._1mag1n33dev.HubHaven.Utils.commands.CommandType;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnNpcCommand extends AbstractSubCommand {
    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        if (args.length < 1) {
            sender.sendMessage("Usage: /hh spawnnpc <name>");
            return true;
        }

        String name = args[1];
        Player player = (Player) sender;

        NPCManager npcManager = plugin.getPacketManager().getNPCManager();
        npcManager.createNPC(name, player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ());

        player.sendMessage("NPC created: " + name);
        return true;
    }

    @Override
    public CommandType getType() {
        return CommandType.ADMIN;
    }
}
