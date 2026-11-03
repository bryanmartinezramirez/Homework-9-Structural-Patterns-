package com.example;

import java.util.HashMap;
import java.util.Map;

public class CharacterStyleFactory {

    private record StyleKey(String font, int size, String color) {}

    private static final Map<StyleKey, CharacterStyle> cache =
        new HashMap<>();

    public static CharacterStyle getStyle(
            String font, int size, String color) {

        StyleKey key = new StyleKey(font, size, color);

        return cache.computeIfAbsent(
            key,
            k -> new CharacterStyle(font, size, color)
        );
    }

    public static int getCacheSize() {
        return cache.size();
    }
}
