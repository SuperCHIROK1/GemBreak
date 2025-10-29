package me.superchirok1.gembreak;

import org.bukkit.configuration.ConfigurationSection;

import java.util.List;

public class BlockData {

    private final int amount;
    private final boolean vanillaDrop;
    private final ConfigurationSection conditions;
    private final ConfigurationSection particle;
    private final ConfigurationSection sound;
    private final ConfigurationSection item;

    public ConfigurationSection getItem() {
        return item;
    }

    public ConfigurationSection getSound() {
        return sound;
    }

    public ConfigurationSection getParticle() {
        return particle;
    }

    public ConfigurationSection getConditions() {
        return conditions;
    }

    public boolean isVanillaDrop() {
        return vanillaDrop;
    }

    public BlockData(ConfigurationSection s) {
        this.amount = s.getInt("amount", 1);
        this.vanillaDrop = s.getBoolean("vanilla-drop", false);
        this.conditions = s.getConfigurationSection("conditions");
        this.particle = s.getConfigurationSection("particle");
        this.sound = s.getConfigurationSection("sound");
        this.item = s.getConfigurationSection("item");
    }

    public int getAmount() {
        return amount;
    }
}
