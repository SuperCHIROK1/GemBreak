package me.superchirok1.gembreak.item;

import org.bukkit.configuration.ConfigurationSection;

import java.util.List;

public class ItemData {

    private final String material;
    private final String name;
    private final List<String> lore;
    private final List<String> enchants;
    private final List<String> flags;

    public String getMaterial() {
        return material;
    }

    public String getName() {
        return name;
    }

    public List<String> getLore() {
        return lore;
    }

    public List<String> getEnchants() {
        return enchants;
    }

    public List<String> getFlags() {
        return flags;
    }

    public ItemData(ConfigurationSection s) {
        this.material = s.getString("material", "STONE").toUpperCase();
        this.name = s.getString("name", "");
        this.lore = s.getStringList("lore");
        this.enchants = s.getStringList("enchants");
        this.flags = s.getStringList("flags");
    }

}
