package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TextCharacterTest {

    @Test
    void testCharacterProperties() {
        CharacterStyle style =
            CharacterStyleFactory.getStyle("Arial", 12, "Red");

        TextCharacter character = new TextCharacter('H', style);

        assertEquals('H', character.getValue());
        assertSame(style, character.getStyle());
    }
}
