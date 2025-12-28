package me.superchirok1.gembreak.placeholder;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;

public class EventPlaceholder {

    public static String parse(String text, BlockBreakEvent event) {
        Block block = event.getBlock();
        Player player = event.getPlayer();

        return text
                .replace("%player%", player.getName())
                .replace("%uuid%", player.getUniqueId().toString())
                .replace("%world%", block.getWorld().getName())
                .replace("%block_x%", String.valueOf(block.getX()))
                .replace("%block_y%", String.valueOf(block.getY()))
                .replace("%block_z%", String.valueOf(block.getZ()))
                .replace("%item%", player.getInventory().getItemInMainHand().getType().name());
    }

}
