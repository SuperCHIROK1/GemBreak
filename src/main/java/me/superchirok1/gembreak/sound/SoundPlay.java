package me.superchirok1.gembreak.sound;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.event.Event;
import org.bukkit.event.block.BlockBreakEvent;

public class SoundPlay {

    public void play(SoundData s, BlockBreakEvent event) {

        if (!s.isEnabled()) return;

        Location location;
        if (s.getLocation().equalsIgnoreCase("block")) {
            location = event.getBlock().getLocation();
        } else {
            location = event.getPlayer().getLocation();
        }

        location.getWorld().playSound(location, Sound.valueOf(s.getId()), s.getVolume(), s.getPitch());

    }

}
