package com.github._1mag1n33dev.HubHaven.Events;

import com.github._1mag1n33dev.HubHaven.Utils.Events.AbstractEvent;
import com.github._1mag1n33dev.HubHaven.Utils.Events.Event;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerJoin extends AbstractEvent {



    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        ItemStack compass = getPlugin().getConfigManager().getCompassItem();
        boolean hasCompass = false;
        for (ItemStack item : player.getInventory().getContents()) {
            if (item != null && item.isSimilar(compass)) {
                hasCompass = true;
                break;
            }
        }
        if (!hasCompass) {
            player.getInventory().addItem(compass);
        }
    }


}
