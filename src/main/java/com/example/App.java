package com.example;

import java.nio.file.Path;

public class App {

    public static void main(String[] args) throws Exception {

        System.out.println("=== FLYWEIGHT TEXT EDITOR ===");

        CharacterStyle style1 =
            CharacterStyleFactory.getStyle("Arial", 12, "Red");

        CharacterStyle style2 =
            CharacterStyleFactory.getStyle("Calibri", 14, "Blue");

        CharacterStyle style3 =
            CharacterStyleFactory.getStyle("Verdana", 16, "Black");

        CharacterStyle style4 =
            CharacterStyleFactory.getStyle("Arial", 14, "Blue");

        Document document = new Document();

        String text = "HelloWorldCS580X";

        for (int i = 0; i < text.length(); i++) {
            CharacterStyle style;

            if (i < 5) {
                style = style1;
            } else if (i < 10) {
                style = style2;
            } else if (i < 12) {
                style = style3;
            } else {
                style = style4;
            }

            document.addCharacter(text.charAt(i), style);
        }

        System.out.println("Before editing: " + document.getText());

        document.editCharacter(15, '0', style4);

        System.out.println("After editing: " + document.getText());

        Path file = Path.of("hw9_document.properties");

        document.save(file);

        System.out.println("Saved to: " + file);

        Document loaded = Document.load(file);

        System.out.println("Loaded text: " + loaded.getText());

        int[] positions = {0, 5, 10, 12};

        for (int position : positions) {
            CharacterStyle style =
                loaded.getCharacter(position).getStyle();

            System.out.printf(
                "Character %d: %s, %d, %s%n",
                position,
                style.getFont(),
                style.getSize(),
                style.getColor());
        }

        System.out.println(
            "Shared style reused: " +
            (loaded.getCharacter(0).getStyle() ==
             loaded.getCharacter(1).getStyle()));

        System.out.println(
            "Cached character styles: " +
            CharacterStyleFactory.getCacheSize());
    }
}
