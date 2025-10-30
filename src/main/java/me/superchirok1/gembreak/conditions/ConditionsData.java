package me.superchirok1.gembreak.conditions;

import lombok.Getter;
import org.bukkit.configuration.ConfigurationSection;

@Getter
public class ConditionsData {

    private final String world;
    private final String biome;
    private final String time;
    private final String y;

    public ConditionsData(ConfigurationSection section) {
        if (section == null) {
            this.world = null;
            this.biome = null;
            this.time = null;
            this.y = null;
            return;
        }

        this.world = normalize(section.getString("world", ""));
        this.biome = normalize(section.getString("biome", ""));
        this.time = normalize(section.getString("time", ""));
        this.y = normalize(section.getString("y", ""));
    }

    private String normalize(String value) {
        return (value == null || value.isEmpty()) ? null : value;
    }
}
