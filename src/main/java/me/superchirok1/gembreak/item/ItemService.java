package me.superchirok1.gembreak.item;

import me.superchirok1.gembreak.GemBreak;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemService {

    private final GemBreak plugin;
    public Map<String, ItemStack> items = new HashMap<>();

    public ItemService(GemBreak plugin) {
        this.plugin = plugin;
    }

    public void init() {
        var colorizer = plugin.colorizer.get;

        List<String> itemsFiles = plugin.config.get.itemsFiles();

        items.clear();
        for (String itemFile : itemsFiles) {

            File file = new File(plugin.getDataFolder(), itemFile);

            if (!file.exists()) {
                Bukkit.getLogger().warning("Файл " + itemFile + " не найден в папке плагина.");
                continue;
            }

            YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

            ConfigurationSection section = config.getConfigurationSection("items");

            for (String key : section.getKeys(false)) {

                ConfigurationSection itemSec = section.getConfigurationSection(key);

                String itemMaterial = itemSec.getString("material", "STONE").toUpperCase().replace(".", "_");
                int itemAmount = itemSec.getInt("amount", 1);

                ItemStack item = new ItemStack(Material.valueOf(itemMaterial), itemAmount);

                String itemName = colorizer.colorize
                        (itemSec.getString("name", itemSec.getString("display_name", item.getType().name())));
                List<String> itemLore = formatList(itemSec.getStringList("lore"));
                List<String> itemEnchants = itemSec.getStringList("enchants");
                List<String> itemFlags = itemSec.getStringList("flags");
                int customModelData = itemSec.getInt("custom_model_data", 0);

                ItemMeta meta = item.getItemMeta();

                meta.setDisplayName(itemName);
                meta.setLore(itemLore);
                for (String itemEnchant : itemEnchants) {
                    String[] args = itemEnchant.split(" ");

                    String enchantName = args[0].toUpperCase().replace(".", "_");
                    int enchantLevel = Integer.parseInt(args[1]);

                    Enchantment enchant = Enchantment.getByName(enchantName);
                    if (enchant == null) {
                        Bukkit.getLogger().warning("Неизвестное зачарование: " + enchantName + " для предмета " + key);
                        continue;
                    }

                    meta.addEnchant(enchant, enchantLevel, true);
                }
                for (String itemFlag : itemFlags) {
                    ItemFlag flag = ItemFlag.valueOf(itemFlag);
                    if (flag == null) {
                        Bukkit.getLogger().warning("Неизвестный флаг: " + itemFlag + " для предмета " + key);
                        continue;
                    }
                    meta.addItemFlags(flag);
                }
                meta.setCustomModelData(customModelData);

                item.setItemMeta(meta);

                items.put(key, item);

            }

        }
    }

    private List<String> formatList(List<String> list) {
        if (list == null) return List.of();
        return list.stream().map(line -> plugin.colorizer.get.colorize(line)).toList();
    }

}
