package me.superchirok1.gembreak.particle;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

public class ParticleSpawn {


    public void spawn(ParticleData p, Location location) {
        if (!p.isEnabled()) return;

        Location center = location.clone().add(0.5, 0.5, 0.5);

        center.getWorld().spawnParticle(
                Particle.valueOf(p.getId()),
                center,
                p.getCount(),
                p.getOffsetX(),
                p.getOffsetY(),
                p.getOffsetZ(),
                p.getExtra()
        );
    }


}
