package com.github._1mag1n33dev.HubHaven.Commands.Debug;

import com.github._1mag1n33dev.HubHaven.Utils.NBTUtils;
import com.github._1mag1n33dev.HubHaven.Utils.StringUtils;
import com.github._1mag1n33dev.HubHaven.Utils.commands.AbstractSubCommand;
import com.github._1mag1n33dev.HubHaven.Utils.commands.CommandType;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.BlockIterator;

import java.util.List;

public class getNbt extends AbstractSubCommand {

    private final Gson gson;

    public getNbt() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length < 1) {
                player.sendMessage(ChatColor.RED + "Usage: /hubhaven getnbt {item | entity | block}");
                return true;
            }

            if (!args[0].equalsIgnoreCase("getnbt")) {
                player.sendMessage(ChatColor.RED + "Invalid command. Use: /hubhaven getnbt {item | entity | block}");
                return true;
            }

            if (args.length < 2) {
                player.sendMessage(ChatColor.RED + "Specify a target: item, entity, or block.");
                return true;
            }

            String targetType = args[1].toLowerCase();
            player.sendMessage(ChatColor.GREEN + "Debug: Target type = " + targetType);

            switch (targetType) {
                case "item":
                    ItemStack itemInHand = player.getItemInHand();
                    if (itemInHand != null && itemInHand.getType() != Material.AIR) {
                        String itemNBT = NBTUtils.getAllNBTTags(itemInHand);
                        player.sendMessage(ChatColor.GREEN + "Item NBT Data:");
                        player.sendMessage(ChatColor.YELLOW + formatNBTData(itemNBT));
                    } else {
                        player.sendMessage(ChatColor.RED + "You are not holding any item.");
                    }
                    break;
                case "block":
                    Block targetBlock = getTargetBlock(player, 5);
                    if (targetBlock != null && targetBlock.getType() != Material.AIR) {
                        String blockNBT = NBTUtils.getAllNBTTags(targetBlock);
                        player.sendMessage(ChatColor.GREEN + "Block NBT Data:");
                        player.sendMessage(ChatColor.YELLOW + formatNBTData(blockNBT));
                    } else {
                        player.sendMessage(ChatColor.RED + "You are not looking at any block.");
                    }
                    break;
                case "entity":
                    Entity targetEntity = getTargetEntity(player, 5);
                    if (targetEntity != null) {
                        String entityNBT = NBTUtils.getAllNBTTags(targetEntity);
                        plugin.getLogger().info(entityNBT);
                        player.sendMessage(ChatColor.GREEN + "Entity NBT Data:");
                        player.sendMessage(ChatColor.YELLOW + formatNBTData(entityNBT));
                    } else {
                        player.sendMessage(ChatColor.RED + "You are not looking at any entity.");
                    }
                    break;
                default:
                    player.sendMessage(ChatColor.RED + "Invalid target type. Use: item, entity, or block.");
                    break;
            }

            return true;
        }
        return false;
    }

    private Block getTargetBlock(Player player, int range) {
        BlockIterator iter = new BlockIterator(player, range);
        Block lastBlock = iter.next();
        while (iter.hasNext()) {
            lastBlock = iter.next();
            if (lastBlock.getType() != Material.AIR) {
                break;
            }
        }
        return lastBlock;
    }

    private Entity getTargetEntity(Player player, int range) {
        List<Entity> nearbyEntities = player.getNearbyEntities(range, range, range);
        Entity closestEntity = null;
        double closestDistance = Double.MAX_VALUE;

        for (Entity entity : nearbyEntities) {
            if (entity.getLocation().distance(player.getLocation()) < closestDistance) {
                closestDistance = entity.getLocation().distance(player.getLocation());
                closestEntity = entity;
            }
        }

        return closestEntity;
    }

    private String formatNBTData(String nbtData) {
        if (nbtData == null || nbtData.isEmpty()) {
            return ChatColor.RED + "No NBT data available.";
        }
        try {
            return formatNBT(nbtData, 0);
        } catch (Exception e) {
            return ChatColor.RED + "Failed to format NBT data.";
        }
    }

    private String formatNBT(String nbtData, int indentLevel) {
        StringBuilder sb = new StringBuilder();
        String indent = StringUtils.createIndent(indentLevel);

        String[] lines = nbtData.split(",");
        for (String line : lines) {
            sb.append(indent).append(line.trim()).append("\n");
        }

        return sb.toString();
    }



    @Override
    public CommandType getType() {
        return CommandType.DEVELOPER;
    }
}
