package me.superchirok1.gembreak.action.impl;

import me.superchirok1.gembreak.action.Action;
import me.superchirok1.gembreak.action.ActionParser;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;

public class SoundAction implements Action {

    @Override
    public String getPrefix() {
        return "sound";
    }

    @Override
    public void execute(String text, BlockBreakEvent event) {
        Player player = event.getPlayer();
        String[] args = ActionParser.parse(text);

        Sound sound = Sound.valueOf(args[0].toUpperCase().replace(".", "_"));

        float volume = 1.0f;
        float pitch = 1.0f;

        if (args.length > 1) {
            volume = (float) Double.parseDouble(args[1]);
        }
        if (args.length > 2) {
            pitch = (float) Double.parseDouble(args[2]);
        }

        player.playSound(player.getLocation(), sound, volume, pitch);

    }

}
