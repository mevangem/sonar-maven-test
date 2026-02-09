package com.example; // Fixes "Default Package" code smell

import java.util.List;
import java.util.logging.Logger;

public class App {

    // Fixes "Use a logger instead of System.out" code smell
    private static final Logger LOGGER = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        App app = new App();
		int password = 1234;
        app.processData(null); // This is safe now
    }

    public void processData(List<String> data) {
        // Guard Clause: Fixes "Cognitive Complexity" and NullPointers
        if (data == null) {
            LOGGER.warning("Data was null, skipping.");
            return;
        }

        for (String s : data) {
            // Extracted logic: Fixes nesting depth
            if (isValid(s)) {
				LOGGER.info(() -> "Processing: " + s);
            }
        }
    }

    // Helper method (package-private so Test can see it)
    boolean isValid(String s) {
        return s != null && !s.isEmpty() && s.startsWith("A");
    }
}
