package me.superchirok1.gembreak.item;

import lombok.Getter;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;

@Getter
public class ItemData {

    private final String material;
    private final String name;
    private final List<String> lore;
    private final List<String> enchants;
    private final List<String> flags;

    public ItemData(ConfigurationSection section) {
        this.material = section.getString("material", "STONE").toUpperCase();
        this.name = section.getString("name", "");
        this.lore = section.getStringList("lore");
        this.enchants = section.getStringList("enchants");
        this.flags = section.getStringList("flags");
    }

}
