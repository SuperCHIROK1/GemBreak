package me.superchirok1.gembreak;

import org.bukkit.plugin.java.JavaPlugin;

public final class GemBreak extends JavaPlugin {
    @Override
    public void onEnable() {
        // Материалы канала @ruspigotru
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new Listener(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
