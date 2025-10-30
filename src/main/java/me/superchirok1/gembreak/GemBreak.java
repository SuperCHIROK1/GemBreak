package me.superchirok1.gembreak;

import me.superchirok1.gembreak.command.ReloadCommand;
import me.superchirok1.gembreak.data.Config;
import me.superchirok1.gembreak.listener.Listener;
import me.superchirok1.gembreak.util.Text;
import org.bukkit.plugin.java.JavaPlugin;

public final class GemBreak extends JavaPlugin {


    @Override
    public void onEnable() {
        saveDefaultConfig();

        config = new Config(this);

        new Text(this);

        getServer().getPluginManager().registerEvents(new Listener(this), this);
        getCommand("gembreak").setExecutor(new ReloadCommand(this));
    }

    private Config config;

    public Config getPluginConfig() {
        return config;
    }

    public void reloadPluginConfig() {
        reloadConfig();
        config = new Config(this);
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
