package me.superchirok1.gembreak.particle;

import lombok.Getter;
import org.bukkit.configuration.ConfigurationSection;

@Getter
public class ParticleData {

    private final boolean enabled;
    private final String id;
    private final double offsetX;
    private final double offsetY;
    private final double offsetZ;
    private final int count;
    private final double extra;

    public ParticleData(ConfigurationSection section) {
        this.enabled = section.getBoolean("enabled", false);
        this.id = section.getString("id", "FLAME");
        this.offsetX = section.getDouble("offset.x", 0);
        this.offsetY = section.getDouble("offset.y", 0);
        this.offsetZ = section.getDouble("offset.z", 0);
        this.count = section.getInt("count", 10);
        this.extra = section.getDouble("extra", 0);
    }

}
