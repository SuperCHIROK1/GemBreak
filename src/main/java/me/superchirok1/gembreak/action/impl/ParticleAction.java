package me.superchirok1.gembreak.action.impl;

import me.superchirok1.gembreak.action.Action;
import me.superchirok1.gembreak.action.ActionParser;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;

public class ParticleAction implements Action {
    @Override
    public String getPrefix() {
        return "particle";
    }

    @Override
    public void execute(String text, BlockBreakEvent event) {
        String[] args = text.split(" ");
        Block block = event.getBlock();
        double offsetX = 0;
        double offsetY = 0;
        double offsetZ = 0;

        if (args.length == 6) {
            offsetX = Double.parseDouble(args[3]);
            offsetY = Double.parseDouble(args[4]);
            offsetZ = Double.parseDouble(args[5]);
        }

        block.getWorld().spawnParticle(
                Particle.valueOf(args[0].toUpperCase()),
                block.getLocation().add(0.5,0.5, 0.5),
                Integer.parseInt(args[1]),
                offsetX, offsetY, offsetZ,
                Double.parseDouble(args[2])
        );
    }
}
