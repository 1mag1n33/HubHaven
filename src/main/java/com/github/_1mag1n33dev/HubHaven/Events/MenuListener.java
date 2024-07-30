package com.github._1mag1n33dev.HubHaven.Events;

import com.github._1mag1n33dev.HubHaven.HubHaven;
import com.github._1mag1n33dev.HubHaven.Utils.Events.AbstractEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.entity.Player;
import org.bukkit.Material;

public class MenuListener extends AbstractEvent {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player) {
            Player player = (Player) event.getWhoClicked();
            if (event.getView().getTitle().equals("Navigator")) {
                event.setCancelled(true);
                ItemStack clickedItem = event.getCurrentItem();

                if (clickedItem != null && clickedItem.getType() != Material.AIR) {
                    player.sendMessage("You clicked on: " + clickedItem.getType().name());
                }
            }
        }
    }
}
