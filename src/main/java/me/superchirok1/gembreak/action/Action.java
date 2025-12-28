package me.superchirok1.gembreak.action;

import org.bukkit.event.block.BlockBreakEvent;

public interface Action {
    String getPrefix();
    void execute(String text, BlockBreakEvent event);
}
