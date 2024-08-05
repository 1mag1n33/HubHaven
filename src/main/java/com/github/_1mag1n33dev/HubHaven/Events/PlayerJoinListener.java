package com.github._1mag1n33dev.HubHaven.Events;

import com.github._1mag1n33dev.HubHaven.HubHaven;
import com.github._1mag1n33dev.HubHaven.Utils.Events.AbstractEvent;
import com.github._1mag1n33dev.HubHaven.Utils.HotbarUtils;
import com.github._1mag1n33dev.HubHaven.Utils.NBTUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerJoinListener extends AbstractEvent {

    private final HotbarUtils hotbarUtils;

    public PlayerJoinListener(HubHaven plugin) {
        super.setPlugin(plugin);
        this.hotbarUtils = new HotbarUtils(plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        hotbarUtils.applyHotbarConfig(player);
        hotbarUtils.makeInventoryUndroppable(player);

    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player) {
            Player player = (Player) event.getWhoClicked();
            ItemStack clickedItem = event.getCurrentItem();

            if (clickedItem != null && NBTUtils.hasNBTTag(clickedItem, "Immovable")) {
                event.setCancelled(true);
            }

            if (event.getView().getTitle().equals(plugin.getConfig().getString("navigator-menu.title", "&aNavigator Menu"))) {
                event.setCancelled(true);
            }
        }
    }


}
