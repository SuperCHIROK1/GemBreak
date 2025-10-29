package me.superchirok1.gembreak.item;

import me.superchirok1.gembreak.util.Text;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemBuilder {

    public ItemStack build(ItemData data) {
        ItemStack i = new ItemStack(Material.valueOf(data.getMaterial()));
        ItemMeta m = i.getItemMeta();

        if (data.getName() != null) {
            try {
                m.setDisplayName(Text.format(data.getName()));
            } catch (Exception e) {
                // Ignore
            }
        }
        if (data.getLore() != null && !data.getLore().isEmpty()) {
            try {
                m.setLore(Text.formatList(data.getLore()));
            } catch (Exception e) {
                // Ignore
            }
        }
        if (data.getEnchants() != null && !data.getEnchants().isEmpty()) {
            for (String enchant : data.getEnchants()) {
                String[] parts = enchant.split(";");
                if (parts.length >= 2) {
                    Enchantment ench = Enchantment.getByName(parts[0].toUpperCase());
                    if (ench != null) {
                        m.addEnchant(ench, Integer.parseInt(parts[1]), false);
                    }
                }
            }
        }
        if (data.getFlags() != null && !data.getFlags().isEmpty()) {
            for (String flag : data.getFlags()) {
                try {
                    m.addItemFlags(ItemFlag.valueOf(flag));
                } catch (IllegalArgumentException e) {
                    // Ignore
                }
            }
        }

        i.setItemMeta(m);
        return i;
    }

}
