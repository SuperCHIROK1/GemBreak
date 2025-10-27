package me.superchirok1.gembreak;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

public class Config {

    // Плагин
    private final GemBreak pl;

    private final boolean enabled, delay;
    private final int delayTicks;
    private final ConfigurationSection blocks;

    // Материалы канала @ruspigotru
    public Config(GemBreak pl) {
        this.pl = pl;
        // Подключаем конфиг
        FileConfiguration conf = pl.getConfig();

        // Получаем данные из конфига
        this.enabled = conf.getBoolean("enabled");
        this.delay = conf.getBoolean("delay");
        this.delayTicks = conf.getInt("delay-ticks");
        this.blocks = conf.getConfigurationSection("blocks");
    }

    // Геттеры (так сказать получатели)
    public boolean isEnabled() {return enabled;}
    public boolean isDelay() {return delay;}
    public int getDelayTicks() {return delayTicks;}
    public ConfigurationSection getBlocks() {return blocks;}

    // Находиться ли блок в разделе blocks из конфига
    public boolean hasInBlocks(Material material) {
        return blocks != null && blocks.contains(material.name().toLowerCase());
    }
    // Получить ConfigurationSection
    public ConfigurationSection getBlock(Material material) {
        if (hasInBlocks(material)) {
            return getBlocks().getConfigurationSection(material.name().toLowerCase());
        }
        return null;
    }

}
