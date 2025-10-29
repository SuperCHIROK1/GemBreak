package me.superchirok1.gembreak.util;
import me.superchirok1.gembreak.GemBreak;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Text {

    private static GemBreak plugin;

    public Text(GemBreak plugin) {
        this.plugin = plugin;
    }

    public static String format(String message) {
        if (message == null) return "";

        final Pattern hexPattern = Pattern.compile("&#([A-Fa-f0-9]{6})");
        Matcher matcher = hexPattern.matcher(message);
        StringBuffer buffer = new StringBuffer();
        while (matcher.find()) {
            String hex = matcher.group(1);
            matcher.appendReplacement(buffer, ChatColor.of("#" + hex).toString());
        }
        matcher.appendTail(buffer);
        message = buffer.toString();

        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static List<String> formatList(List<String> list) {
        if (list == null) return List.of();
        return list.stream().map(line -> format(line)).toList();
    }

}

