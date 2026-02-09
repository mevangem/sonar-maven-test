package com.example;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;

class AppTest {

    @Test
    void testAppFlow() {
        App app = new App();
        app.processData(null);
        app.processData(Collections.singletonList("Alpha"));
        app.processData(Arrays.asList("Beta", ""));
        App.main(new String[]{});
    }
}
