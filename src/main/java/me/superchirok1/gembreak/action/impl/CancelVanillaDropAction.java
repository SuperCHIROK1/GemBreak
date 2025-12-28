package me.superchirok1.gembreak.action.impl;

import me.superchirok1.gembreak.action.Action;
import org.bukkit.event.block.BlockBreakEvent;

public class CancelVanillaDropAction implements Action {
    @Override
    public String getPrefix() {
        return "cancel_vanilla_drop";
    }

    @Override
    public void execute(String text, BlockBreakEvent event) {
        event.setDropItems(false);
    }
}
