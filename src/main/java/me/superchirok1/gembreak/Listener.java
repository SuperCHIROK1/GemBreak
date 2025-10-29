package me.superchirok1.gembreak;

import me.superchirok1.gembreak.conditions.ConditionChecker;
import me.superchirok1.gembreak.conditions.ConditionsData;
import me.superchirok1.gembreak.item.ItemBuilder;
import me.superchirok1.gembreak.item.ItemData;
import me.superchirok1.gembreak.particle.ParticleData;
import me.superchirok1.gembreak.particle.ParticleSpawn;
import me.superchirok1.gembreak.sound.SoundData;
import me.superchirok1.gembreak.sound.SoundPlay;
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

    public Listener(GemBreak pl) {
        this.pl = pl;
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Config data = pl.getPluginConfig();

        Block block = e.getBlock();
        if (!data.isEnabled()) return;
        if (e.getPlayer().getGameMode() != GameMode.SURVIVAL) return;
        if (!data.hasInBlocks(block.getType())) return;

        BlockData blockData = new BlockData(data.getBlock(block.getType()));

        if (blockData.getConditions() != null) {
            if (!new ConditionChecker().check(new ConditionsData(blockData.getConditions()), e)) {
                return;
            }
        }

        e.setDropItems(blockData.isVanillaDrop());

        ItemStack item = new ItemBuilder().build(new ItemData(blockData.getItem()));

        if (data.isDelay()) {
            int ticks = data.getDelayTicks();

            for (int i = 0; i < blockData.getAmount(); i++) {
                int delay = ticks * i;

                Bukkit.getScheduler().runTaskLater(pl, () -> {
                    block.getLocation().getWorld().dropItemNaturally(block.getLocation(), item);
                    soundParticle(blockData, e);
                }, delay);
            }
        } else {
            item.setAmount(blockData.getAmount());
            block.getLocation().getWorld().dropItemNaturally(block.getLocation(), item);
            soundParticle(blockData, e);
        }
    }

    private void soundParticle(BlockData blockData, BlockBreakEvent e) {
        SoundPlay sound = new SoundPlay();
        ParticleSpawn particle = new ParticleSpawn();

        if (blockData.getSound() != null) {
            sound.play(new SoundData(blockData.getSound()), e);
        }
        if (blockData.getParticle() != null) {
            particle.spawn(new ParticleData(blockData.getParticle()), e.getBlock().getLocation());
        }
    }

}
