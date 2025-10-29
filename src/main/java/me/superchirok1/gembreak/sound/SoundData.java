package me.superchirok1.gembreak.sound;

import org.bukkit.configuration.ConfigurationSection;

public class SoundData {

    private final boolean enabled;
    private final String id;
    private final float volume;
    private final float pitch;
    private final String location;

    public boolean isEnabled() {
        return enabled;
    }

    public String getId() {
        return id;
    }

    public float getVolume() {
        return volume;
    }

    public float getPitch() {
        return pitch;
    }

    public String getLocation() {
        return location;
    }

    public SoundData(ConfigurationSection s) {
        this.enabled = s.getBoolean("enabled", false);
        this.id = s.getString("id", "entity.item.pickup").toUpperCase().replace(".", "_");
        this.volume = (float) s.getDouble("volume", 1);
        this.pitch = (float) s.getDouble("pitch", 1);
        this.location = s.getString("location", "BLOCK").toUpperCase();
    }

}
