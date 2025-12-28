package me.superchirok1.gembreak.action.impl;

import me.superchirok1.gembreak.GemBreak;
import me.superchirok1.gembreak.action.Action;
import me.superchirok1.gembreak.placeholder.EventPlaceholder;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.event.block.BlockBreakEvent;

public class ActionbarAction implements Action {

    @Override
    public String getPrefix() {
        return "actionbar";
    }

    @Override
    public void execute(String text, BlockBreakEvent event) {
        event.getPlayer().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(text));
    }
}
