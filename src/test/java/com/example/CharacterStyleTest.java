package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CharacterStyleTest {

    @Test
    void testStyleProperties() {
        CharacterStyle style =
            CharacterStyleFactory.getStyle("Arial", 12, "Red");

        assertEquals("Arial", style.getFont());
        assertEquals(12, style.getSize());
        assertEquals("Red", style.getColor());
    }

    @Test
    void testSharedStyle() {
        CharacterStyle first =
            CharacterStyleFactory.getStyle("Calibri", 14, "Blue");

        CharacterStyle second =
            CharacterStyleFactory.getStyle("Calibri", 14, "Blue");

        assertSame(first, second);
    }

    @Test
    void testDifferentStyles() {
        CharacterStyle first =
            CharacterStyleFactory.getStyle("Arial", 12, "Red");

        CharacterStyle second =
            CharacterStyleFactory.getStyle("Verdana", 16, "Black");

        assertNotSame(first, second);
    }

    @Test
    void testCacheSize() {
        CharacterStyleFactory.getStyle("Arial", 12, "Red");
        int before = CharacterStyleFactory.getCacheSize();

        CharacterStyleFactory.getStyle("Arial", 12, "Red");

        assertEquals(before, CharacterStyleFactory.getCacheSize());
    }
}
