package me.superchirok1.gembreak.data;

import lombok.Getter;
import me.superchirok1.gembreak.GemBreak;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

@Getter
public class Config {

    private final GemBreak pl;

    private final boolean enabled, delay;
    private final int delayTicks;
    private final ConfigurationSection blocks;

    private final String reloaded;
    private final String noPerms;

    public Config(GemBreak pl) {
        this.pl = pl;
        FileConfiguration conf = pl.getConfig();

        this.enabled = conf.getBoolean("enabled");
        this.delay = conf.getBoolean("delay");
        this.delayTicks = conf.getInt("delay-ticks");
        this.blocks = conf.getConfigurationSection("blocks");

        this.reloaded = conf.getString("messages.reloaded", "Перезагружен!");
        this.noPerms = conf.getString("messages.no-perms", "Нет прав");
    }

    public boolean hasInBlocks(Material material) {
        return blocks != null && blocks.contains(material.name().toLowerCase());
    }

    public ConfigurationSection getBlock(Material material) {
        if (hasInBlocks(material)) {
            return getBlocks().getConfigurationSection(material.name().toLowerCase());
        }
        return null;
    }

}
