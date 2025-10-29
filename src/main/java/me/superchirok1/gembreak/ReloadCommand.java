package me.superchirok1.gembreak;

import me.superchirok1.gembreak.util.Text;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ReloadCommand implements CommandExecutor {

    private final GemBreak plugin;

    public ReloadCommand(GemBreak plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (!commandSender.hasPermission("gemblock.reload")) {
            commandSender.sendMessage(Text.format(new Config(plugin).getNoPerms()));
            return true;
        }

        plugin.reloadPluginConfig();
        commandSender.sendMessage(Text.format(new Config(plugin).getReloaded()));
        return true;
    }
}
