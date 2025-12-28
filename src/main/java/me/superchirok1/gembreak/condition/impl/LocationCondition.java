package me.superchirok1.gembreak.condition.impl;

import me.superchirok1.gembreak.condition.Condition;
import org.bukkit.event.block.BlockBreakEvent;

public class LocationCondition implements Condition {
    @Override
    public String prefix() {
        return "location";
    }

    @Override
    public boolean condition(String text, BlockBreakEvent event) {
        String[] args = text.split(" ");
        if (args.length < 4) return false;

        int x = event.getBlock().getX();
        int y = event.getBlock().getY();
        int z = event.getBlock().getZ();
        String world = event.getBlock().getWorld().getName();

        if (!check(args[0], x)) return false;
        if (!check(args[1], y)) return false;
        if (!check(args[2], z)) return false;

        return args[3].equalsIgnoreCase(world);
    }

    private boolean check(String text, int value)
    {
        int index = text.indexOf("..");

        if (index==-1) return value==Integer.parseInt(text);

        if (index==0||index+2==text.length()) return true;

        String[] args = text.split("\\.\\.");
        int min = Integer.parseInt(args[0]);
        int max = Integer.parseInt(args[1]);

        return value >= min && value <= max;
    }

}
