package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class DocumentTest {

    @Test
    void testAddCharacterAndGetText() {
        Document document = new Document();

        CharacterStyle style =
            CharacterStyleFactory.getStyle("Arial", 12, "Red");

        document.addCharacter('H', style);
        document.addCharacter('i', style);

        assertEquals("Hi", document.getText());
        assertEquals(2, document.size());
        assertEquals('H', document.getCharacter(0).getValue());

        assertSame(
            document.getCharacter(0).getStyle(),
            document.getCharacter(1).getStyle());
    }

    @Test
    void testEditCharacter() {
        Document document = new Document();

        CharacterStyle original =
            CharacterStyleFactory.getStyle("Arial", 12, "Red");

        CharacterStyle updated =
            CharacterStyleFactory.getStyle("Calibri", 14, "Blue");

        document.addCharacter('A', original);
        document.editCharacter(0, 'B', updated);

        assertEquals("B", document.getText());
        assertSame(updated, document.getCharacter(0).getStyle());
    }

    @Test
    void testSaveAndLoad(@TempDir Path tempDir) throws IOException {
        Document document = new Document();

        CharacterStyle style =
            CharacterStyleFactory.getStyle("Verdana", 16, "Black");

        document.addCharacter('H', style);
        document.addCharacter('i', style);

        Path file = tempDir.resolve("test_document.properties");

        document.save(file);

        assertTrue(Files.exists(file));

        Document loaded = Document.load(file);

        assertEquals("Hi", loaded.getText());
        assertEquals(2, loaded.size());

        assertSame(style, loaded.getCharacter(0).getStyle());
        assertSame(
            loaded.getCharacter(0).getStyle(),
            loaded.getCharacter(1).getStyle());
    }
}
