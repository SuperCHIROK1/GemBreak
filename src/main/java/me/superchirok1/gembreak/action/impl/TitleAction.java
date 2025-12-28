package me.superchirok1.gembreak.action.impl;

import me.superchirok1.gembreak.GemBreak;
import me.superchirok1.gembreak.action.Action;
import me.superchirok1.gembreak.action.ActionParser;
import me.superchirok1.gembreak.color.Colorizer;
import me.superchirok1.gembreak.placeholder.EventPlaceholder;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;

public class TitleAction implements Action {

    @Override
    public String getPrefix() {
        return "title";
    }

    @Override
    public void execute(String text, BlockBreakEvent event) {
        String[] args = ActionParser.parse(text);
        Player player = event.getPlayer();

        if (player == null || args.length == 0) return;

        String title = args[0];
        String subtitle = args[1];

        if (args.length == 2) {
            player.sendTitle(title, subtitle, 10, 20, 10);
        } else {
            int fadeIn = Integer.parseInt(args[2]);
            int stay = Integer.parseInt(args[3]);
            int fadeOut = Integer.parseInt(args[4]);

            player.sendTitle(title, subtitle, fadeIn, stay, fadeOut);
        }
    }
}
