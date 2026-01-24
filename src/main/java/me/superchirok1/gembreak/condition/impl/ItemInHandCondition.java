package me.superchirok1.gembreak.condition.impl;

import me.superchirok1.gembreak.condition.Condition;
import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;

public class ItemInHandCondition implements Condition {
    @Override
    public String prefix() {
        return "item_in_hand";
    }

    @Override
    public boolean condition(String text, BlockBreakEvent event) {
        return event.getPlayer().getInventory().getItemInMainHand().getType() == Material.getMaterial(text.toUpperCase());
    }
}
