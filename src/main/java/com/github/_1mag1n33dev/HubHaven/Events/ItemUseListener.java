package com.github._1mag1n33dev.HubHaven.Events;

import com.github._1mag1n33dev.HubHaven.HubHaven;
import com.github._1mag1n33dev.HubHaven.Menu.Menu;
import com.github._1mag1n33dev.HubHaven.Utils.Events.AbstractEvent;
import com.github._1mag1n33dev.HubHaven.Utils.NBTUtils;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ItemUseListener extends AbstractEvent {

    public ItemUseListener(HubHaven plugin) {
        super.setPlugin(plugin);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (item != null && item.hasItemMeta()) {
            String action = NBTUtils.getNBTTag(item, "action");
            if (action != null) {
                parseActions(player, action);
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player) {
            Player player = (Player) event.getWhoClicked();
            ItemStack item = event.getCurrentItem();

            if (item != null && item.hasItemMeta()) {
                String isMenuItem = NBTUtils.getNBTTag(item, "menuItem");
                if ("true".equals(isMenuItem)) { // Check if item is a menu item
                    String action = NBTUtils.getNBTTag(item, "action");
                    if (action != null) {
                        parseActions(player, action);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        ItemStack item = event.getItemDrop().getItemStack();
        if (item != null && NBTUtils.hasNBTTag(item, "undroppable")) {
            event.setCancelled(true);
        }
    }

    private void parseActions(Player player, String action) {
        if (action.startsWith("/")) {
            String command = action.substring(1);
            player.performCommand(command);
        } else {
            switch (action) {
                case "openNavigatorMenu":
                    Menu menu = new Menu(plugin, "Navigator.Menu");
                    player.openInventory(menu.getInventory());
                    break;
                case "teleport_arena":
                    player.teleport(new Location(player.getWorld(), 100, 65, 100)); // Example coordinates
                    player.sendMessage("Teleported to Arena!");
                    break;
                default:
                    player.sendMessage("Unknown action: " + action);
                    break;
            }
        }
    }
}
