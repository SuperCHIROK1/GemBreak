package me.superchirok1.gembreak.color;

import me.superchirok1.gembreak.color.impl.LegacyColorizerInterface;
import me.superchirok1.gembreak.color.impl.MiniMessageColorizerInterface;

public class Colorizer {

    public ColorizerInterface get;

    public void init(String colorizer) {
        get = colorizer.equalsIgnoreCase("minimessage")
                ? new MiniMessageColorizerInterface() : new LegacyColorizerInterface();
    }

}
