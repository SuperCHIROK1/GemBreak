package me.superchirok1.gembreak.action.impl;

import me.superchirok1.gembreak.GemBreak;
import me.superchirok1.gembreak.action.Action;
import me.superchirok1.gembreak.placeholder.EventPlaceholder;
import org.bukkit.event.block.BlockBreakEvent;

public class MessageAction implements Action {

    @Override
    public String getPrefix() {
        return "message";
    }

    @Override
    public void execute(String text, BlockBreakEvent event) {
        event.getPlayer().sendMessage(text);
    }

}
