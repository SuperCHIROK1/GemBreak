package me.superchirok1.gembreak.config.record;

import java.util.List;

public record ConfigValues(
    boolean metrics,
    String colorizer,
    List<String> listenerFiles,
    List<String> itemsFiles,
    String permissionReload,
    String msgsPrefix,
    String msgsReloaded,
    String msgsNoPerms
) {}
