package me.superchirok1.gembreak.action.impl;

import me.superchirok1.gembreak.action.Action;
import me.superchirok1.gembreak.placeholder.EventPlaceholder;
import org.bukkit.Bukkit;
import org.bukkit.event.block.BlockBreakEvent;

public class ExecuteConsoleAction implements Action {
    @Override
    public String getPrefix() {
        return "execute_console";
    }

    @Override
    public void execute(String text, BlockBreakEvent event) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), text);
    }

}
