package com.example;

public class TextCharacter {

    private final char value;
    private final CharacterStyle style;

    public TextCharacter(char value, CharacterStyle style) {
        this.value = value;
        this.style = style;
    }

    public char getValue() {
        return value;
    }

    public CharacterStyle getStyle() {
        return style;
    }
}
