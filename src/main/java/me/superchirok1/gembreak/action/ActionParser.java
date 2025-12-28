package me.superchirok1.gembreak.action;

import org.apache.commons.text.StringTokenizer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ActionParser {

    public static String[] parse(String text) {
        StringTokenizer tokenizer = new StringTokenizer(text);
        tokenizer.setQuoteChar('"');
        return tokenizer.getTokenArray();
    }

}
