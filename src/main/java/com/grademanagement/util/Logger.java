package com.grademanagement.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utility class for logging system events
 */
public class Logger {
    private static final String LOG_FILE = "logs/system.log";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Log an information message
     */
    public static void info(String message) {
        log("INFO", message);
    }

    /**
     * Log a warning message
     */
    public static void warning(String message) {
        log("WARNING", message);
    }

    /**
     * Log an error message
     */
    public static void error(String message) {
        log("ERROR", message);
    }

    /**
     * Log an error with exception
     */
    public static void error(String message, Exception e) {
        log("ERROR", message + " - " + e.getMessage());
        e.printStackTrace();
    }

    /**
     * Internal method to write log entries
     */
    private static void log(String level, String message) {
        try {
            // Create logs directory if it doesn't exist
            File logFile = new File(LOG_FILE);
            File logDir = logFile.getParentFile();
            if (logDir != null && !logDir.exists()) {
                logDir.mkdirs();
            }

            try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE, true))) {
                String timestamp = LocalDateTime.now().format(FORMATTER);
                writer.println("[" + timestamp + "] [" + level + "] " + message);
            }
        } catch (IOException e) {
            // Silently fail - don't interrupt application flow
            // System.err.println("Failed to write to log file: " + e.getMessage());
        }
    }
}
