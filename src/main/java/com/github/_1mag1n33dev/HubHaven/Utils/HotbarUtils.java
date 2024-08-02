package com.github._1mag1n33dev.HubHaven.Utils;

import com.github._1mag1n33dev.HubHaven.HubHaven;
import com.github._1mag1n33dev.HubHaven.Menu.Menu;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Map;

public class HotbarUtils {

    private final HubHaven plugin;

    public HotbarUtils(HubHaven plugin) {
        if (plugin == null) {
            throw new IllegalArgumentException("Plugin cannot be null");
        }
        this.plugin = plugin;
    }

    public static void addItemToHotbar(Player player, Material material, String name, int slot, String action, boolean immovable) {
        if (slot < 0 || slot >= 9) {
            throw new IllegalArgumentException("Hotbar slot must be between 0 and 8");
        }

        Inventory inventory = player.getInventory();
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(name);
            item.setItemMeta(meta);
        }
        item = NBTUtils.setNBTTag(item, "action", action);
        item = NBTUtils.setNBTTag(item, "immovable", Boolean.toString(immovable));
        inventory.setItem(slot, item);
        player.getOpenInventory().setItem(slot, item);
    }


    public void applyHotbarConfig(Player player) {
        if (player == null || plugin == null) {
            throw new IllegalArgumentException("Player or plugin cannot be null");
        }

        FileConfiguration config = this.plugin.getConfig();
        if (config == null) {
            throw new IllegalArgumentException("Config cannot be null");
        }

        List<Map<?, ?>> items = config.getMapList("Hotbar.items");
        player.getInventory().clear();
        if (items != null) {
            for (Map<?, ?> itemConfig : items) {
                try {
                    Integer slot = (Integer) itemConfig.get("slot");
                    String materialString = (String) itemConfig.get("material");
                    String name = (String) itemConfig.get("name");
                    String action = (String) itemConfig.get("action");
                    Boolean immovable = (Boolean) itemConfig.get("immovable");


                    if (slot == null) {
                        System.out.println("Slot is null for item: " + itemConfig);
                        continue;
                    }
                    if (materialString == null) {
                        System.out.println("Material string is null for item: " + itemConfig);
                        continue;
                    }
                    if (name == null) {
                        System.out.println("Name is null for item: " + itemConfig);
                        continue;
                    }
                    if (action == null) {
                        System.out.println("Action is null for item: " + itemConfig);
                        continue;
                    }
                    if (immovable == null) {
                        System.out.println("Immovable is null for item: " + itemConfig);
                        continue;
                    }

                    Material material;
                    try {
                        material = Material.valueOf(materialString);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid material: " + materialString);
                        continue;
                    }

                    addItemToHotbar(player, material, name, slot, action, immovable);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("No items found in hotbar configuration.");
        }
    }

    public void handleHotbarClick(InventoryClickEvent event) {
        ItemStack clickedItem = event.getCurrentItem();
        if (clickedItem != null && !clickedItem.getType().equals(Material.AIR)) {
            String immovable = NBTUtils.getNBTTag(clickedItem, "immovable");
            if (immovable != null) {
                boolean isImmovable = Boolean.parseBoolean(immovable);
                if (isImmovable) {

                    event.setCancelled(true);
                }
            }
        }
    }

    public static void makeInventoryUndroppable(Player player) {
        Inventory inventory = player.getInventory();
        for (int i = 0; i < inventory.getSize(); i++) {
            ItemStack item = inventory.getItem(i);
            if (item != null) {
                item = NBTUtils.setNBTTag(item, "undroppable", "true");
                inventory.setItem(i, item);
            }
        }
    }

}
