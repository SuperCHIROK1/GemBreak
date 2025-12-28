package me.superchirok1.gembreak.model;

import java.util.List;

public record GBlock(
        boolean enabled,
        String blockId,
        List<String> conditions,
        List<String> allowActions,
        boolean allowRepeatEnabled,
        int allowRepeatDelay,
        int allowRepeatAmount,
        List<String> allowRepeatActions,
        List<String> denyActions,
        boolean denyRepeatEnabled,
        int denyRepeatDelay,
        int denyRepeatAmount,
        List<String> denyRepeatActions,
        List<String> alwaysActions,
        boolean alwaysRepeatEnabled,
        int alwaysRepeatDelay,
        int alwaysRepeatAmount,
        List<String> alwaysRepeatActions
) {}
