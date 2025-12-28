package me.superchirok1.gembreak.action.impl;

import me.superchirok1.gembreak.action.Action;
import me.superchirok1.gembreak.placeholder.EventPlaceholder;
import org.bukkit.event.block.BlockBreakEvent;

public class ExecutePlayerAction implements Action {
    @Override
    public String getPrefix() {
        return "execute_player";
    }

    @Override
    public void execute(String text, BlockBreakEvent event) {
        event.getPlayer().performCommand(text);
    }
}
