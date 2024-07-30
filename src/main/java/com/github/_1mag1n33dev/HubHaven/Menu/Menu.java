package com.github._1mag1n33dev.HubHaven.Menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Menu implements InventoryHolder {

    private final Inventory inventory;

    public Menu(String title, int size) {
        this.inventory = Bukkit.createInventory(this, size, title);
    }

    public void addItem(Material material, String name, int amount, int slot) {
        ItemStack item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(name);
            item.setItemMeta(meta);
        }
        inventory.setItem(slot, item);
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }
}
