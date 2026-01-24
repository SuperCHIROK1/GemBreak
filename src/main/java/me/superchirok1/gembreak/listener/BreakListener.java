package me.superchirok1.gembreak.listener;

import me.superchirok1.gembreak.GemBreak;
import me.superchirok1.gembreak.action.repeat.RepeatExecutor;
import me.superchirok1.gembreak.model.GBlock;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class BreakListener implements Listener {

    private final GemBreak plugin;
    private final Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();

    public BreakListener(GemBreak plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {

        Block block = e.getBlock();
        var actionProvider = plugin.actionProvider;
        var repeatExecutor = plugin.repeatExecutor;

        GBlock gBlock = plugin.blockService.getBlockByType(block.getType());

        if (gBlock == null) return;

        actionProvider.execute(e, gBlock.alwaysActions());
        repeatExecutor.repeat(RepeatExecutor.RepeatType.ALWAYS, e, gBlock);

        if (plugin.conditionManager.condition(e, gBlock.conditions())) {
            actionProvider.execute(e, gBlock.allowActions());
            repeatExecutor.repeat(RepeatExecutor.RepeatType.ALLOW, e, gBlock);
        } else {
            actionProvider.execute(e, gBlock.denyActions());
            repeatExecutor.repeat(RepeatExecutor.RepeatType.DENY, e, gBlock);
        }

    }

    @EventHandler
    public void onPickup(EntityPickupItemEvent event) {
        removeFromTeam(event.getItem());
    }

    @EventHandler
    public void onDespawn(ItemDespawnEvent event) {
        removeFromTeam(event.getEntity());
    }

    private void removeFromTeam(Entity entity) {
        if (!(entity instanceof Item)) return;

        String entry = entity.getUniqueId().toString();
        Team team = scoreboard.getEntryTeam(entry);
        if (team != null && team.getName().startsWith("gb_")) {
            team.removeEntry(entry);
        }
    }


}
