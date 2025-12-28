package me.superchirok1.gembreak.condition.impl;

import me.superchirok1.gembreak.condition.Condition;
import org.bukkit.World;
import org.bukkit.event.block.BlockBreakEvent;

public class TimeCondition implements Condition {
    @Override
    public String prefix() {
        return "time";
    }

    @Override
    public boolean condition(String text, BlockBreakEvent event) {
        World world = event.getBlock().getWorld();
        String[] args = text.split("-");

        int value1 = Integer.parseInt(args[0]);
        int value2 = Integer.parseInt(args[1]);

        return world.getTime() >= value1 && world.getTime() <= value2;
    }
}
