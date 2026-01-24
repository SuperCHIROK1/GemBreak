package me.superchirok1.gembreak.config;

import me.superchirok1.gembreak.GemBreak;
import me.superchirok1.gembreak.color.Colorizer;
import me.superchirok1.gembreak.config.record.ConfigValues;
import org.bukkit.configuration.file.FileConfiguration;

public class Config {

    public ConfigValues get;
    private String prefix = "";

    private final GemBreak plugin;

    public Config(GemBreak plugin) {
        this.plugin = plugin;
    }

    public void init(FileConfiguration config) {
        prefix = config.getString("messages.prefix", "");

        get = new ConfigValues(
                config.getBoolean("main_settings.metrics", true),
                config.getString("main_settings.colorizer", "minimessage"),
                config.getStringList("main_settings.listeners_files"),
                config.getStringList("main_settings.items_files"),
                config.getString("main_settings.permissions.reload", "gembreak.reload"),
                prefix,
                format(config.getString("messages.reloaded", "")),
                format(config.getString("messages.no_perms", ""))
        );
    }

    private String format(String text) {
        text = text.replace("%prefix%", prefix);
        return Colorizer.get.colorize(text);
    }

}
