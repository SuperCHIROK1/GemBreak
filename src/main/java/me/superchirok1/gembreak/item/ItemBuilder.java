package me.superchirok1.gembreak.item;

import me.superchirok1.gembreak.util.Text;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemBuilder {

    public ItemStack build(ItemData data) {
        ItemStack item = new ItemStack(Material.valueOf(data.getMaterial()));
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return item;

        if (data.getName() != null) {
            meta.setDisplayName(Text.format(data.getName()));
        }

        if (data.getLore() != null && !data.getLore().isEmpty()) {
            meta.setLore(Text.formatList(data.getLore()));
        }

        if (data.getEnchants() != null && !data.getEnchants().isEmpty()) {
            for (String enchant : data.getEnchants()) {
                String[] parts = enchant.split(";");
                if (parts.length >= 2) {
                    String enchantName = parts[0].toLowerCase();
                    int level;
                    try {
                        level = Integer.parseInt(parts[1]);
                    } catch (NumberFormatException e) {
                        continue;
                    }

                    Enchantment ench = Enchantment.getByKey(NamespacedKey.minecraft(enchantName));
                    if (ench != null) {
                        meta.addEnchant(ench, level, true);
                    }
                }
            }
        }

        if (data.getFlags() != null && !data.getFlags().isEmpty()) {
            for (String flag : data.getFlags()) {
                try {
                    meta.addItemFlags(ItemFlag.valueOf(flag.toUpperCase()));
                } catch (IllegalArgumentException ignored) {}
            }
        }

        item.setItemMeta(meta);
        return item;
    }
}
