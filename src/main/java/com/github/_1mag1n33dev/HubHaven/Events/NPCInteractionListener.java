package com.github._1mag1n33dev.HubHaven.Events;

import com.github._1mag1n33dev.HubHaven.HubHaven;
import com.github._1mag1n33dev.HubHaven.Utils.NBTUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class NPCInteractionListener implements Listener {
    private final HubHaven plugin;

    public NPCInteractionListener(HubHaven plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEntityEvent event) {
        if (NBTUtils.hasNBTTag(event.getRightClicked(), "HubHavenNPC")) {
            Player player = event.getPlayer();
            event.setCancelled(true); // Cancel the event to prevent any default action
            plugin.getLogger().info("Interacted with NPC");

            // Open a custom menu or perform an action
            openCustomMenu(player);
        }
    }

    private void openCustomMenu(Player player) {
        // Create a simple inventory for demonstration
        Inventory inventory = plugin.getServer().createInventory(null, 9, ChatColor.GREEN + "NPC Menu");

        // Add some items to the inventory
        ItemStack item = new ItemStack(Material.DIAMOND);
        inventory.addItem(item);

        // Open the inventory for the player
        player.openInventory(inventory);
    }
}
