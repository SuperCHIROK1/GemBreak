package me.superchirok1.gembreak.action.impl;

import me.superchirok1.gembreak.GemBreak;
import me.superchirok1.gembreak.action.Action;
import me.superchirok1.gembreak.color.Colorizer;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Item;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class DropItemAction implements Action {

    private final GemBreak plugin;
    private final Scoreboard scoreboard;

    public DropItemAction(GemBreak plugin) {
        this.plugin = plugin;
        this.scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
    }

    @Override
    public String getPrefix() {
        return "drop_item";
    }

    @Override
    public void execute(String text, BlockBreakEvent event) {
        var values = plugin.itemService.items.get(text);
        if (values == null) return;

        ItemStack itemStack = values.item();
        Item item = event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), itemStack);

        String colorName = values.glow();

        if (colorName != null && !colorName.equalsIgnoreCase("none")) {

            try {
                ChatColor textColor = ChatColor.valueOf(colorName.toUpperCase());

                item.setGlowing(true);
                String teamName = "gb_" + colorName.toLowerCase();
                Team team = scoreboard.getTeam(teamName);

                if (team == null) {
                    team = scoreboard.registerNewTeam(teamName);
                }

                team.setColor(textColor);
                team.addEntry(item.getUniqueId().toString());
            } catch (IllegalArgumentException e) {
                plugin.getLogger().warning("Цвет свечения '" + colorName + "' не найден");
            }

        }

        if (values.displayName() != null && !values.displayName().isEmpty()) {
            item.setCustomName(Colorizer.get.colorize(event.getPlayer(), values.displayName()));
            item.setCustomNameVisible(true);
        }
    }

}
