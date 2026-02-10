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
		app.criticalFailures();
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

	/**
     * This method is designed to fail a SonarQube Quality Gate.
     */
    public void criticalFailures() {
        // 1. VULNERABILITY (Critical) - Hardcoded Password
        // Sonar will flag this because secrets should never be in source code.
        String password = "admin_password_123!"; 

        // 2. BUG (Blocker) - Null Pointer Dereference
        // Sonar's data flow analysis will see that 'ptr' is null 
        // and then immediately accessed.
        String ptr = null;
        if (true) {
            System.out.println(ptr.length()); 
        }

        // 3. CODE SMELL (Major) - Empty Catch Block
        // This is "swallowing" an exception, which makes debugging impossible.
        try {
            int result = 10 / 0;
        } catch (Exception e) {
            // Do nothing
        }
    }
}
