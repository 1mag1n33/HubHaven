package com.github._1mag1n33dev.HubHaven.Commands.npc;

import com.github._1mag1n33dev.HubHaven.Utils.commands.AbstractSubCommand;
import com.github._1mag1n33dev.HubHaven.Utils.commands.CommandType;
import com.github._1mag1n33dev.HubHavenApi.npc.EntityRegistry;
import com.github._1mag1n33dev.HubHavenApi.npc.NPC;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import net.minecraft.server.v1_8_R3.WorldServer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.entity.Player;

import java.nio.Buffer;
import java.util.Set;

public class SpawnNpcCommand extends AbstractSubCommand {

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be used by players.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length < 3) {
            player.sendMessage("Usage: /hubhaven spawnnpc <type> <name>");
            return true;
        }

        String type = args[1];
        String name = args[2];
        Location location = player.getLocation();


        Set<String> validTypes = EntityRegistry.getTypes();
        if (!validTypes.contains(type)) {
            player.sendMessage("Invalid NPC type. Valid types are: " + String.join(", ", validTypes));
            return true;
        }
        MinecraftServer minecraftServer = ((CraftServer) Bukkit.getServer()).getServer();
        WorldServer worldServer = ((CraftWorld) player.getWorld()).getHandle();

        NPC npc = plugin.getNPCManager().createNPC(type, minecraftServer, worldServer);
        if (npc != null) {
            npc.spawn(location, name);
            player.sendMessage("Spawned NPC: " + name + " of type " + type + " at your location.");
        } else {
            player.sendMessage("Failed to spawn NPC. Check the type and try again.");
        }

        return true;
    }

    @Override
    public CommandType getType() {
        return CommandType.ADMIN;
    }
}
