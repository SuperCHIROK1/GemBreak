package me.superchirok1.gembreak;

import org.bukkit.configuration.ConfigurationSection;

public class BlockData {

    private final String item;
    private final int amount;

    // Материалы канала @ruspigotru
    public BlockData(ConfigurationSection s) {
        // Получение
        this.item = s.getString("item");
        this.amount = s.getInt("amount");
    }

    public String getItem() {
        return item;
    }

    public int getAmount() {
        return amount;
    }
}
