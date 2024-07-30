package com.github._1mag1n33dev.HubHaven.Events;

import com.github._1mag1n33dev.HubHaven.HubHaven;
import com.github._1mag1n33dev.HubHaven.Menu.Menu;
import com.github._1mag1n33dev.HubHaven.Utils.Events.AbstractEvent;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.entity.Player;

public class CompassEvent extends AbstractEvent {

    @EventHandler
    public void onPlayerUseCompass(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player player = event.getPlayer();
            ItemStack item = event.getItem();

            if (item != null && item.getType() == getPlugin().getConfigManager().getCompassMaterial()) {
                ItemMeta meta = item.getItemMeta();
                if (meta != null && getPlugin().getConfigManager().getCompassDisplayName().equals(meta.getDisplayName())) {
                    event.setCancelled(true);  // Prevents further processing of the item use.
                    openMenu(player);
                }
            }
        }
    }

    private void openMenu(Player player) {
        removeExtraCompasses(player);

        Menu menu = new Menu("Navigator", 27); // Change size and title as needed
        menu.addItem(Material.DIAMOND_SWORD, "Sword", 1, 10);  // Example items
        menu.addItem(Material.GOLDEN_APPLE, "Apple", 5, 12);

        player.openInventory(menu.getInventory());
    }

    private void removeExtraCompasses(Player player) {
        ItemStack compass = new ItemStack(Material.COMPASS);
        ItemMeta meta = compass.getItemMeta();
        if (meta != null) {
            meta.setDisplayName("Navigator");
            compass.setItemMeta(meta);
        }

        for (ItemStack item : player.getInventory().getContents()) {
            if (item != null && item.isSimilar(compass)) {
                player.getInventory().remove(item);
            }
        }
    }
}

