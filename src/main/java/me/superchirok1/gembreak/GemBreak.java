package me.superchirok1.gembreak;

import me.superchirok1.gembreak.action.ActionProvider;
import me.superchirok1.gembreak.action.repeat.RepeatExecutor;
import me.superchirok1.gembreak.block.BlockService;
import me.superchirok1.gembreak.color.Colorizer;
import me.superchirok1.gembreak.color.impl.MiniMessageColorizerInterface;
import me.superchirok1.gembreak.command.GemBreakCommand;
import me.superchirok1.gembreak.condition.ConditionManager;
import me.superchirok1.gembreak.config.Config;
import me.superchirok1.gembreak.item.ItemService;
import me.superchirok1.gembreak.listener.BreakListener;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class GemBreak extends JavaPlugin {

    public Colorizer colorizer;
    public ItemService itemService;
    public Config config;

    public BlockService blockService;
    public ConditionManager conditionManager;
    public ActionProvider actionProvider;
    public RepeatExecutor repeatExecutor;

    private final List<String> files = Arrays.asList("blocks/blocks.yml", "items/items.yml", "blocks/ores.yml", "blocks/other.yml");

    public boolean isFirstLaunch;

    private void firstStartSave() {
        if (!isFirstLaunch) return;

        for (String file : files) {
            File configFile = new File(getDataFolder(), file);

            if (!configFile.exists()) {
                saveResource(file, false);
            }
        }
    }

    @Override
    public void onEnable() {

        isFirstLaunch = !getDataFolder().exists();;

        saveDefaultConfig();
        firstStartSave();

        ConfigurationSection settings = getConfig().getConfigurationSection("main_settings");

        colorizer = new Colorizer();
        config = new Config(this);
        itemService = new ItemService(this);
        blockService = new BlockService(this);

        colorizer.init(settings.getString("colorizer"));
        config.init(getConfig());
        List<String> listenerFiles = config.get.listenerFiles();
        itemService.init();
        blockService.init();

        conditionManager = new ConditionManager();
        conditionManager.init();
        actionProvider = new ActionProvider(this);
        actionProvider.init();

        repeatExecutor = new RepeatExecutor(this);

        getCommand("gembreak").setExecutor(new GemBreakCommand(this));
        getServer().getPluginManager().registerEvents(new BreakListener(this), this);

        if (config.get.metrics()) {
            new Metrics(this, 28559);
        }

        if (isFirstLaunch) {
            var console = getServer().getConsoleSender();
            MiniMessageColorizerInterface mm = new MiniMessageColorizerInterface();

            Bukkit.getScheduler().runTaskLater(this, () -> {
                console.sendMessage(mm.colorize("<aqua>================================================================"));
                console.sendMessage("");
                console.sendMessage(mm.colorize("<#FFD799>GemBreak <gray>| <yellow>Обнаружен первый запуск!"));
                console.sendMessage(mm.colorize("<white>Спасибо, что выбрали наш плагин для кастомного дропа."));
                console.sendMessage(mm.colorize("<white>По возможности, оставьте <#FFD799>рецензию <white>на странице плагина."));
                console.sendMessage(mm.colorize("<gray>Если вы нашли баг, напишите нам в личные сообщения или обсуждение."));
                console.sendMessage("");
                console.sendMessage(mm.colorize("<aqua>================================================================"));
            }, 200);

        }

    }

    @Override
    public void onDisable() {


    }

}
