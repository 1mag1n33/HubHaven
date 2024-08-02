package com.github._1mag1n33dev.HubHaven.Config;

import com.github._1mag1n33dev.HubHaven.HubHaven;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Map;

public class ConfigManager {
    private final HubHaven hubHaven;
    private final FileConfiguration config;

    public ConfigManager(HubHaven hubHaven) {
        this.hubHaven = hubHaven;
        this.config = hubHaven.getConfig();
    }

    public String getString(String path, String defaultValue) {
        return config.getString(path, defaultValue);
    }

    public int getInt(String path, int defaultValue) {
        return config.getInt(path, defaultValue);
    }

    public double getDouble(String path, double defaultValue) {
        return config.getDouble(path, defaultValue);
    }

    public boolean getBoolean(String path, boolean defaultValue) {
        return config.getBoolean(path, defaultValue);
    }

    public Object getObject(String path) {
        return config.get(path);
    }

    public List<?> getList(String path) {
        return config.getList(path);
    }

    public List<?> getList(String path, List<?> defaultValue) {
        return config.getList(path, defaultValue);
    }

    public List<Map<?, ?>> getMapList(String path) {
        return config.getMapList(path);
    }

    public List<String> getStringList(String path) {
        return config.getStringList(path);
    }

    public void set(String path, Object value) {
        config.set(path, value);
        saveConfig();
    }

    public boolean contains(String path) {
        return config.contains(path);
    }

    private void saveConfig() {
        hubHaven.saveConfig();
    }


    public Material getCompassMaterial() {
        String materialName = config.getString("compass.material", "COMPASS");
        return Material.getMaterial(materialName);
    }

    public String getCompassDisplayName() {
        return config.getString("compass.display-name", "&bNavigator");
    }

    public List<String> getCompassLore() {
        return config.getStringList("compass.lore");
    }

    public ItemStack getCompassItem() {
        Material material = getCompassMaterial();
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(getCompassDisplayName());
            meta.setLore(getCompassLore());
            item.setItemMeta(meta);
        }
        return item;
    }
}
