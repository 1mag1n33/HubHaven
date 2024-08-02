package com.github._1mag1n33dev.HubHaven.Menu;

import com.github._1mag1n33dev.HubHaven.HubHaven;
import com.github._1mag1n33dev.HubHaven.Utils.NBTUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Map;

public class Menu implements InventoryHolder {

    private final Inventory inventory;
    private final HubHaven plugin;
    private final String configPath;

    public Menu(HubHaven plugin, String configPath) {
        this.plugin = plugin;
        this.configPath = configPath;
        this.inventory = createInventory();
        loadItems();
    }

    private Inventory createInventory() {
        ConfigurationSection menuSection = plugin.getConfig().getConfigurationSection(configPath);
        if (menuSection == null) {
            throw new IllegalArgumentException("Configuration path not found: " + configPath);
        }

        String title = menuSection.getString("title", "&aMenu");
        int size = menuSection.getInt("size", 9);
        return Bukkit.createInventory(this, size, title);
    }

    private void loadItems() {
        ConfigurationSection menuSection = plugin.getConfig().getConfigurationSection(configPath);
        if (menuSection == null) return;

        List<Map<?, ?>> items = menuSection.getMapList("items");
        if (items != null) {
            for (Map<?, ?> itemConfig : items) {
                try {
                    String materialString = (String) itemConfig.get("material");
                    String name = (String) itemConfig.get("name");
                    Integer amount = (Integer) itemConfig.get("amount");
                    Integer slot = (Integer) itemConfig.get("slot");
                    String action = (String) itemConfig.get("action");

                    if (materialString == null || name == null || amount == null || slot == null || action == null) {
                        plugin.getLogger().warning("Missing item configuration: " + itemConfig);
                        continue;
                    }

                    Material material = Material.valueOf(materialString.toUpperCase());
                    addItem(material, name, amount, slot, action);
                } catch (Exception e) {
                    plugin.getLogger().severe("Error loading item: " + itemConfig);
                    e.printStackTrace();
                }
            }
        } else {
            plugin.getLogger().warning("No items found in menu configuration.");
        }
    }

    public void addItem(Material material, String name, int amount, int slot, String action) {
        ItemStack item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(name);
            item.setItemMeta(meta);
        }
        item = NBTUtils.setNBTTag(item, "action", action);
        item = NBTUtils.setNBTTag(item, "menuItem", "true");
        inventory.setItem(slot, item);
    }

    public void updateMenu() {
        inventory.clear();
        loadItems();
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }
}
