package me.superchirok1.gembreak.action.impl;

import me.superchirok1.gembreak.action.Action;
import org.bukkit.event.block.BlockBreakEvent;

public class CancelEventAction implements Action {
    @Override
    public String getPrefix() {
        return "cancel_event";
    }

    @Override
    public void execute(String text, BlockBreakEvent event) {
        event.setCancelled(true);
    }
}
