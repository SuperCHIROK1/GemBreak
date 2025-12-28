package me.superchirok1.gembreak.condition.impl;

import me.superchirok1.gembreak.condition.Condition;
import org.bukkit.event.block.BlockBreakEvent;

public class SneakingCondition implements Condition {
    @Override
    public String prefix() {
        return "sneaking";
    }

    @Override
    public boolean condition(String text, BlockBreakEvent event) {
        return event.getPlayer().isSneaking() == Boolean.parseBoolean(text);
    }
}
