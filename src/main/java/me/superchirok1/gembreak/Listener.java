package me.superchirok1.gembreak;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class Listener implements org.bukkit.event.Listener {

    private final GemBreak pl;
    private final Config data;

    public Listener(GemBreak pl) {
        this.pl = pl;
        this.data = new Config(pl);
    }

    // Материалы канала @ruspigotru
    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Block block = e.getBlock();
        if (!data.isEnabled()) return;
        if (e.getPlayer().getGameMode() != GameMode.SURVIVAL) return;
        if (!data.hasInBlocks(block.getType())) return;

        e.setDropItems(false);

        BlockData blockData = new BlockData(data.getBlock(block.getType()));
        ItemStack item = new ItemStack(Material.valueOf(blockData.getItem().toUpperCase()));

        if (data.isDelay()) {
            int ticks = data.getDelayTicks();
            int count = 0;

            for (int i = 0; i < blockData.getAmount(); i++) {
                int delay = ticks * i;

                Bukkit.getScheduler().runTaskLater(pl, () -> {
                    block.getLocation().getWorld().dropItemNaturally(block.getLocation(), item);
                }, delay);

                count++;
            }
        } else {
            item.setAmount(blockData.getAmount());
            block.getLocation().getWorld().dropItemNaturally(block.getLocation(), item);
        }
    }
}
