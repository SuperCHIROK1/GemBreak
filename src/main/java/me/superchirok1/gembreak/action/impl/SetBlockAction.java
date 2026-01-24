package me.superchirok1.gembreak.action.impl;

import me.superchirok1.gembreak.action.Action;
import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;

public class SetBlockAction implements Action {
    @Override
    public String getPrefix() {
        return "set_block";
    }

    @Override
    public void execute(String text, BlockBreakEvent event) {
        event.getBlock().setType(Material.valueOf(text));
    }
}
