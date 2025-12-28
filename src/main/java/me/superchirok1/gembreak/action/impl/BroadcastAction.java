package me.superchirok1.gembreak.action.impl;

import me.superchirok1.gembreak.GemBreak;
import me.superchirok1.gembreak.action.Action;
import me.superchirok1.gembreak.placeholder.EventPlaceholder;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.event.block.BlockBreakEvent;

public class BroadcastAction implements Action {
    @Override
    public String getPrefix() {
        return "broadcast";
    }

    @Override
    public void execute(String text, BlockBreakEvent event) {
        Bukkit.getServer().broadcastMessage(text);
    }

}
