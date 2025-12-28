package me.superchirok1.gembreak.action.repeat;

import me.superchirok1.gembreak.GemBreak;
import me.superchirok1.gembreak.model.GBlock;
import org.bukkit.Bukkit;
import org.bukkit.event.block.BlockBreakEvent;


public class RepeatExecutor {

    private final GemBreak plugin;

    public RepeatExecutor(GemBreak plugin) {
        this.plugin = plugin;
    }

    public void repeat(RepeatType type, BlockBreakEvent event, GBlock block) {
        if (block == null) return;

        Runnable action;
        int amount, delay;
        boolean enabled;

        switch (type) {
            case ALWAYS -> {
                enabled = block.alwaysRepeatEnabled();
                amount = block.alwaysRepeatAmount();
                delay = block.alwaysRepeatDelay();
                action = () -> plugin.actionProvider.execute(event, block.alwaysRepeatActions());
            }
            case ALLOW -> {
                enabled = block.allowRepeatEnabled();
                amount = block.allowRepeatAmount();
                delay = block.allowRepeatDelay();
                action = () -> plugin.actionProvider.execute(event, block.allowRepeatActions());
            }
            case DENY -> {
                enabled = block.denyRepeatEnabled();
                amount = block.denyRepeatAmount();
                delay = block.denyRepeatDelay();
                action = () -> plugin.actionProvider.execute(event, block.denyRepeatActions());
            }
            default -> throw new IllegalStateException("Неверное значение: " + type);
        }

        if (!enabled) return;

        for (int i = 0; i < amount; i++) {
            Bukkit.getScheduler().runTaskLater(plugin, action, (long) delay * i);
        }
    }


    public enum RepeatType {
        ALWAYS,
        ALLOW,
        DENY
    }

}
