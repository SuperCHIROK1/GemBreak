package me.superchirok1.gembreak.condition;

import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;

public interface Condition {
    String prefix();
    boolean condition(String text, BlockBreakEvent event);
}
