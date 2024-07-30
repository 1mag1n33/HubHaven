package com.github._1mag1n33dev.HubHaven.Commands;

import com.github._1mag1n33dev.HubHaven.Utils.commands.AbstractSubCommand;
import com.github._1mag1n33dev.HubHaven.Utils.commands.CommandType;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashSet;
import java.util.Set;

public class VanishCommand extends AbstractSubCommand {
    private final Set<Player> vanishedPlayers = new HashSet<>();

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        Player player = (Player) sender;

        if (vanishedPlayers.contains(player)) {
            vanishedPlayers.remove(player);
            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                onlinePlayer.showPlayer(player);
            }
            player.removePotionEffect(PotionEffectType.INVISIBILITY);
            player.sendMessage("You are no longer vanished.");
        } else {
            vanishedPlayers.add(player);
            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                onlinePlayer.hidePlayer(player);
            }
            player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1, false, false));
            player.sendMessage("You are now vanished.");
        }
        return true;
    }

    @Override
    public CommandType getType() {
        return CommandType.ADMIN;
    }
}
