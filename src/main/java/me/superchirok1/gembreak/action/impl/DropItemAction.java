package me.superchirok1.gembreak.action.impl;

import me.superchirok1.gembreak.GemBreak;
import me.superchirok1.gembreak.action.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class DropItemAction implements Action {

    private final GemBreak plugin;

    public DropItemAction(GemBreak plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getPrefix() {
        return "drop_item";
    }

    @Override
    public void execute(String text, BlockBreakEvent event) {
        ItemStack item = plugin.itemService.items.get(text);
        event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), item);
    }

}
