package me.superchirok1.gembreak.particle;

import org.bukkit.configuration.ConfigurationSection;

public class ParticleData {

    private final boolean enabled;
    private final String id;
    private final double offsetX;
    private final double offsetY;
    private final double offsetZ;
    private final int count;
    private final double extra;

    public boolean isEnabled() {
        return enabled;
    }

    public String getId() {
        return id;
    }

    public double getOffsetX() {
        return offsetX;
    }

    public double getOffsetY() {
        return offsetY;
    }

    public double getOffsetZ() {
        return offsetZ;
    }

    public int getCount() {
        return count;
    }

    public double getExtra() {
        return extra;
    }

    public ParticleData(ConfigurationSection s) {
        this.enabled = s.getBoolean("enabled", false);
        this.id = s.getString("id", "FLAME");
        this.offsetX = s.getDouble("offset.x", 0);
        this.offsetY = s.getDouble("offset.y", 0);
        this.offsetZ = s.getDouble("offset.z", 0);
        this.count = s.getInt("count", 10);
        this.extra = s.getDouble("extra", 0);
    }

}
