package me.superchirok1.gembreak.condition.impl;


import me.superchirok1.gembreak.condition.Condition;
import org.bukkit.event.block.BlockBreakEvent;

public class GamemodeCondition implements Condition {
    @Override
    public String prefix() {
        return "gamemode";
    }

    @Override
    public boolean condition(String text, BlockBreakEvent event) {
        return event.getPlayer().getGameMode().name().equalsIgnoreCase(text);
    }
}
