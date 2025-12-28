package me.superchirok1.gembreak.block;

import me.superchirok1.gembreak.GemBreak;
import me.superchirok1.gembreak.model.GBlock;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlockService {

    private final GemBreak plugin;
    public Map<String, GBlock> blocks = new HashMap<>();

    public BlockService(GemBreak plugin) {
        this.plugin = plugin;
    }

    public void init() {

        List<String> listenerFiles = plugin.config.get.listenerFiles();

        blocks.clear();
        for (String fileName : listenerFiles) {

            File file = new File(plugin.getDataFolder(), fileName);

            if (!file.exists()) {
                Bukkit.getLogger().warning("Файл " + fileName + " не найден в папке плагина.");
                continue;
            }

            YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

            ConfigurationSection section = config.getConfigurationSection("listeners");

            for (String key : section.getKeys(false)) {

                ConfigurationSection listener = section.getConfigurationSection(key);

                GBlock block = new GBlock(
                        listener.getBoolean("enabled", true),
                        listener.getString("block_id"),
                        listener.getStringList("conditions"),

                        listener.getStringList("actions.allow.actions"),
                        listener.getBoolean("actions.allow.repeat.enabled", false),
                        listener.getInt("actions.allow.repeat.delay", 0),
                        listener.getInt("actions.allow.repeat.amount", 1),
                        listener.getStringList("actions.allow.repeat.actions"),

                        listener.getStringList("actions.deny.actions"),
                        listener.getBoolean("actions.deny.repeat.enabled", false),
                        listener.getInt("actions.deny.repeat.delay", 0),
                        listener.getInt("actions.deny.repeat.amount", 1),
                        listener.getStringList("actions.deny.repeat.actions"),

                        listener.getStringList("actions.always.actions"),
                        listener.getBoolean("actions.always.repeat.enabled", false),
                        listener.getInt("actions.always.repeat.delay", 0),
                        listener.getInt("actions.always.repeat.amount", 1),
                        listener.getStringList("actions.always.repeat.actions")
                );

                blocks.put(key, block);

            }

        }

    }

    public boolean blockTypeHas(Material material) {
        boolean found = false;
        for (GBlock block : blocks.values()) {
            found = block.blockId().equalsIgnoreCase(material.name());
        }
        return found;
    }

    public GBlock getBlock(String blockId) {
        return blocks.get(blockId);
    }

    public GBlock getBlockByType(Material material) {
        GBlock gblock = null;
        for (GBlock block : blocks.values()) {
            if (!block.blockId().equalsIgnoreCase(material.name())) continue;
            gblock = block;
        }
        return gblock;
    }

}
