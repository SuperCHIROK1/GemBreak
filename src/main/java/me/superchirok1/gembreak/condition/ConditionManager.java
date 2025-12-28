package me.superchirok1.gembreak.condition;

import me.superchirok1.gembreak.condition.impl.*;
import me.superchirok1.gembreak.placeholder.EventPlaceholder;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConditionManager {

    private final Map<String, Condition> conditions = new HashMap<>();

    public void init() {
        register(new BiomeCondition());
        register(new TimeCondition());
        register(new LocationCondition());
        register(new PermissionCondition());
        register(new GamemodeCondition());
        register(new SneakingCondition());
    }

    public void register(Condition condition) {
        conditions.put(condition.prefix().toLowerCase(), condition);
    }

    public boolean condition(BlockBreakEvent event, List<String> conditionsList) {
        for (String condition : conditionsList) {
            boolean reversed = false;

            if (condition.startsWith("!")) {
                reversed = true;
                condition = condition.substring(1);
            }

            boolean matched = false;

            for (Condition c : conditions.values()) {

                String prefix = c.prefix().toLowerCase() + ":";
                if (!condition.toLowerCase().startsWith(prefix)) continue;

                String arg = condition.substring(prefix.length()).trim();
                boolean result = c.condition(EventPlaceholder.parse(arg, event), event);

                if (reversed) result = !result;

                matched = result;
                break;

            }

            if (!matched) return false;
        }

        return true;
    }


}
