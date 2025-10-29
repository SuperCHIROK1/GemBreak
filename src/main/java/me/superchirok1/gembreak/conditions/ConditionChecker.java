package me.superchirok1.gembreak.conditions;

import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;

public class ConditionChecker {

    public boolean check(ConditionsData data, BlockBreakEvent event) {
        Block block = event.getBlock();

        boolean world = true;
        boolean biome = true;
        boolean time = true;
        boolean y = true;

        if (data.getWorld() != null && !block.getWorld().getName().equalsIgnoreCase(data.getWorld())) {
            world = false;
        }
        if (data.getBiome() != null && !block.getWorld().getBiome(block.getX(), block.getZ()).name().equalsIgnoreCase(data.getBiome())) {
            biome = false;
        }
        if (data.getTime() != null && !checkTime(data.getTime(), block.getWorld())) {
            time = false;
        }
        if (data.getY() != null && !checkY(data.getY(), block)) {
            y = false;
        }

        return world && biome && time && y;
    }

    public boolean checkTime(String time, World world) {
        if (time == null || !time.contains("-")) return true;

        String[] t = time.split("-");
        if (t.length != 2) return true;

        try {
            int startHour = Integer.parseInt(t[0]);
            int endHour = Integer.parseInt(t[1]);

            int start = (startHour % 24) * 1000;
            int end = (endHour % 24) * 1000;

            long current = world.getTime() % 24000;

            if (start < end) {
                return current >= start && current < end;
            } else {
                return current >= start || current < end;
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
            return true;
        }
    }

    public boolean checkY(String a, Block block) {
        if (a == null || !a.contains("-")) {
            return true;
        }

        String[] b = a.split("-");
        if (b.length != 2) {
            return true;
        }

        try {
            int minY = Integer.parseInt(b[0].trim());
            int maxY = Integer.parseInt(b[1].trim());
            int y = block.getY();

            return y >= minY && y <= maxY;

        } catch (NumberFormatException e) {
            e.printStackTrace();
            return true;
        }
    }


}
