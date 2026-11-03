package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    void testAppRuns() {
        assertDoesNotThrow(() -> App.main(new String[0]));
    }
}