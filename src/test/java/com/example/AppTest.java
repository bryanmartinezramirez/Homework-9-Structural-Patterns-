package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;

class AppTest {

    @Test
    void testFlyweightDriver() throws Exception {
        PrintStream original = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        try {
            System.setOut(new PrintStream(output));
            App.main(new String[0]);
        } finally {
            System.setOut(original);
        }

        String result = output.toString();

        assertTrue(result.contains(
            "Loaded text: HelloWorldCS5800"));

        assertTrue(result.contains(
            "Shared style reused: true"));

        assertTrue(Files.exists(
            Path.of("hw9_document.properties")));
    }
}
