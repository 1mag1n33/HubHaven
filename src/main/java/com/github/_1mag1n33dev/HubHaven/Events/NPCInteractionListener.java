package com.github._1mag1n33dev.HubHaven.Events;

import com.github._1mag1n33dev.HubHaven.HubHaven;
import com.github._1mag1n33dev.HubHaven.NMS.common.npc.NPC;
import com.github._1mag1n33dev.HubHaven.NMS.common.npc.NPCMetaData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class NPCInteractionListener implements Listener {

    private final HubHaven plugin;

    public NPCInteractionListener(HubHaven plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEntityEvent event) {
        if (event.getRightClicked() instanceof NPC) {
            NPC npc = (NPC) event.getRightClicked();
            Player player = event.getPlayer();

            plugin.getLogger().info("NPC Clicked: " + npc.getNMSEntity().getName());
            event.setCancelled(true);

            openCustomMenu(player, npc);
        }
    }

    private void openCustomMenu(Player player, NPC npc) {

        Inventory inventory = plugin.getServer().createInventory(null, 9, ChatColor.GREEN + "NPC Menu");

        ItemStack item = new ItemStack(Material.DIAMOND);
        inventory.addItem(item);

        player.openInventory(inventory);
    }
}
