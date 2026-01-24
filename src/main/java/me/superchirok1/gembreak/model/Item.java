package me.superchirok1.gembreak.model;

import org.bukkit.inventory.ItemStack;

public record Item(
        ItemStack item, String glow, String displayName
) {}
