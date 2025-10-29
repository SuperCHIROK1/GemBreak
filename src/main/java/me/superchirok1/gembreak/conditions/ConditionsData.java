package me.superchirok1.gembreak.conditions;

import org.bukkit.configuration.ConfigurationSection;

public class ConditionsData {

    private final String world;
    private final String biome;
    private final String time;
    private final String y;

    public String getWorld() {
        return world.isEmpty() ? null : world;
    }
    public String getBiome() {
        return biome.isEmpty() ? null : biome;
    }
    public String getTime() {
        return time.isEmpty() ? null : time;
    }
    public String getY() {
        return y.isEmpty() ? null : y;
    }


    public ConditionsData(ConfigurationSection s) {
        this.world = s.getString("world", "");
        this.biome = s.getString("biome", "");
        this.time = s.getString("time", "");
        this.y = s.getString("y", "");
    }

}
