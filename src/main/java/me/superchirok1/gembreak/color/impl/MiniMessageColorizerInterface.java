package me.superchirok1.gembreak.color.impl;

import me.clip.placeholderapi.PlaceholderAPI;
import me.superchirok1.gembreak.color.ColorizerInterface;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.entity.Player;

public class MiniMessageColorizerInterface implements ColorizerInterface {

    private static final LegacyComponentSerializer LEGACY = LegacyComponentSerializer.builder()
            .character('\u00A7')
            .hexColors()
            .useUnusualXRepeatedCharacterHexFormat()
            .build();

    @Override
    public String colorize(String string) {
        if (string == null) return "";
        Component comp = MiniMessage.miniMessage().deserialize(string);
        return LEGACY.serialize(comp);
    }

    @Override
    public String colorize(Player player, String text) {
        return PlaceholderAPI.setPlaceholders(player, colorize(text));
    }

}
