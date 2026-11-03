package com.example;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Document {

    private final List<TextCharacter> characters = new ArrayList<>();

    public void addCharacter(char value, CharacterStyle style) {
        characters.add(new TextCharacter(value, style));
    }

    public void editCharacter(
            int index, char value, CharacterStyle style) {

        characters.set(index, new TextCharacter(value, style));
    }

    public TextCharacter getCharacter(int index) {
        return characters.get(index);
    }

    public int size() {
        return characters.size();
    }

    public String getText() {
        StringBuilder text = new StringBuilder();

        for (TextCharacter character : characters) {
            text.append(character.getValue());
        }

        return text.toString();
    }

    public void save(Path file) throws IOException {
        Properties data = new Properties();

        data.setProperty("text", getText());

        for (int i = 0; i < characters.size(); i++) {
            CharacterStyle style = characters.get(i).getStyle();

            String value = style.getFont() + "|" +
                           style.getSize() + "|" +
                           style.getColor();

            data.setProperty("style." + i, value);
        }

        try (Writer writer = Files.newBufferedWriter(
                file, StandardCharsets.UTF_8)) {

            data.store(writer, "Flyweight Text Document");
        }
    }

    public static Document load(Path file) throws IOException {
        Properties data = new Properties();

        try (Reader reader = Files.newBufferedReader(
                file, StandardCharsets.UTF_8)) {

            data.load(reader);
        }

        String text = data.getProperty("text");

        if (text == null) {
            throw new IOException("Document text is missing");
        }

        Document document = new Document();

        for (int i = 0; i < text.length(); i++) {
            String styleData = data.getProperty("style." + i);

            if (styleData == null) {
                throw new IOException("Missing style for character " + i);
            }

            String[] parts = styleData.split("\\|", -1);

            if (parts.length != 3) {
                throw new IOException("Invalid character style");
            }

            int size;

            try {
                size = Integer.parseInt(parts[1]);
            } catch (NumberFormatException e) {
                throw new IOException("Invalid font size", e);
            }

            CharacterStyle style = CharacterStyleFactory.getStyle(
                parts[0], size, parts[2]);

            document.addCharacter(text.charAt(i), style);
        }

        return document;
    }
}
