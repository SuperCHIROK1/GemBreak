package me.superchirok1.gembreak.action;

import me.superchirok1.gembreak.GemBreak;
import me.superchirok1.gembreak.action.impl.*;
import me.superchirok1.gembreak.placeholder.EventPlaceholder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.slf4j.event.Level;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class ActionProvider {

    private final GemBreak pl;

    private final Map<String, Action> actions = new HashMap<>();

    public ActionProvider(GemBreak pl) {
        this.pl = pl;
    }

    public void register(Action action) {
        actions.put(action.getPrefix().toLowerCase(), action);
    }

    public void execute(BlockBreakEvent event, List<String> items) {

        if (items == null || items.isEmpty()) return;

        var colorizer = pl.colorizer.get;

        for (String item : items) {
            String prefix = item.substring(item.indexOf("[") + 1, item.indexOf("]")).toLowerCase();;

            String data = item.contains(" ") ? item.split(" ", 2)[1] : "";

            Action action = actions.get(prefix);
            if (action != null) {
                action.execute(
                            colorizer.colorize(event.getPlayer(), EventPlaceholder.parse(data, event)), event);
            } else {
                Bukkit.getLogger().warning("Действие " + prefix + " не найдено");
            }
        }

    }

    private void registerIfPluginPresent(String pluginName, Supplier<Action> supplier) {
        if (Bukkit.getPluginManager().isPluginEnabled(pluginName)) {
            register(supplier.get());
        }
    }

    public void init() {
        register(new ActionbarAction());
        register(new BroadcastAction());
        register(new CancelEventAction());
        register(new CancelVanillaDropAction());
        register(new DropItemAction(pl));
        register(new ExecuteConsoleAction());
        register(new ExecutePlayerAction());
        register(new MessageAction());
        register(new ParticleAction());
        register(new SoundAction());
        register(new TitleAction());
    }

}
