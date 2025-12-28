package me.superchirok1.gembreak.command;

import me.superchirok1.gembreak.GemBreak;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class GemBreakCommand implements CommandExecutor {

    private final GemBreak plugin;

    public GemBreakCommand(GemBreak plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        var config = plugin.config.get;
        if (!commandSender.hasPermission(config.permissionReload())) {
            commandSender.sendMessage(config.msgsNoPerms());
            return true;
        }

        plugin.reloadConfig();
        plugin.colorizer.init(plugin.getConfig().getString("main_settings.colorizer"));
        plugin.config.init(plugin.getConfig());
        plugin.itemService.init();
        plugin.blockService.init();

        commandSender.sendMessage(config.msgsReloaded());
        return true;
    }
}
