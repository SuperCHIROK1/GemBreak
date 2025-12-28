package me.superchirok1.gembreak.condition.impl;

import me.superchirok1.gembreak.condition.Condition;
import org.bukkit.event.block.BlockBreakEvent;

public class BiomeCondition implements Condition {
    @Override
    public String prefix() {
        return "biome";
    }

    @Override
    public boolean condition(String text, BlockBreakEvent event) {
        return event.getBlock().getBiome().name().equalsIgnoreCase(text);
    }
}
