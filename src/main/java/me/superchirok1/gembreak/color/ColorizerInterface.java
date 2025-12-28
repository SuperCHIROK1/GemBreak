package me.superchirok1.gembreak.color;

import org.bukkit.entity.Player;

public interface ColorizerInterface {
    String colorize(String text);
    String colorize(Player player, String text);
}
