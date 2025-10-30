package me.superchirok1.gembreak.sound;

import lombok.Getter;
import org.bukkit.configuration.ConfigurationSection;


@Getter
public class SoundData {

    private final boolean enabled;
    private final String id;
    private final float volume;
    private final float pitch;
    private final String location;

    public SoundData(ConfigurationSection section) {
        this.enabled = section.getBoolean("enabled", false);
        this.id = section.getString("id", "entity.item.pickup").toUpperCase().replace(".", "_");
        this.volume = (float) section.getDouble("volume", 1);
        this.pitch = (float) section.getDouble("pitch", 1);
        this.location = section.getString("location", "BLOCK").toUpperCase();
    }

}
