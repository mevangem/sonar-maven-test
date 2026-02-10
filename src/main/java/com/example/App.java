package com.example;

import java.util.List;
import java.util.logging.Logger;

public class App {

    private static final Logger LOGGER = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        App app = new App();
        
        // We call this safely to avoid crashing the Maven Test phase
        app.processData(null); 
        
        // This is the "Injected Problem"
        // We only call it if a specific property is NOT set.
        // This allows the Scanner to see the code, but keeps the Tests alive.
        if (System.getProperty("sonar.scan") != null) {
            app.criticalFailures();
        }
    }

    public void processData(List<String> data) {
        if (data == null) {
            LOGGER.warning("Data was null, skipping.");
            return;
        }
        for (String s : data) {
            if (isValid(s)) {
                LOGGER.info(() -> "Processing: " + s);
            }
        }
    }

    boolean isValid(String s) {
        return s != null && !s.isEmpty() && s.startsWith("A");
    }

    public void criticalFailures() {
        // 1. VULNERABILITY (Critical)
        String password = "admin_password_123!"; 

        // 2. BUG (Blocker)
        String ptr = null;
        if (Boolean.parseBoolean("true")) { // Obfuscated so Sonar sees the flow but doesn't optimize it away
            System.out.println(ptr.length()); 
        }

        // 3. CODE SMELL (Major)
        try {
            int result = 10 / 0;
        } catch (Exception e) {
            // Empty catch block
        }
    }
}
