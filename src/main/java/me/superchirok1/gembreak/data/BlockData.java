package me.superchirok1.gembreak.data;

import lombok.Getter;
import org.bukkit.configuration.ConfigurationSection;

@Getter
public class BlockData {

    private final int amount;
    private final boolean vanillaDrop;
    private final ConfigurationSection conditions;
    private final ConfigurationSection particle;
    private final ConfigurationSection sound;
    private final ConfigurationSection item;

    public BlockData(ConfigurationSection section) {
        this.amount = section.getInt("amount", 1);
        this.vanillaDrop = section.getBoolean("vanilla-drop", false);
        this.conditions = section.getConfigurationSection("conditions");
        this.particle = section.getConfigurationSection("particle");
        this.sound = section.getConfigurationSection("sound");
        this.item = section.getConfigurationSection("item");
    }

}
