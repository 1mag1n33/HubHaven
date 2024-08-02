package com.github._1mag1n33dev.HubHaven.Events;

import com.github._1mag1n33dev.HubHaven.HubHaven;
import com.github._1mag1n33dev.HubHaven.Utils.HotbarUtils;
import com.github._1mag1n33dev.HubHaven.Utils.Events.AbstractEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;

public class HotbarEventListener extends AbstractEvent {

    private final HotbarUtils hotbarUtils;

    public HotbarEventListener(HubHaven plugin) {
        super.setPlugin(plugin);
        this.hotbarUtils = new HotbarUtils(plugin);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player) {
            hotbarUtils.handleHotbarClick(event);
        }
    }
}
