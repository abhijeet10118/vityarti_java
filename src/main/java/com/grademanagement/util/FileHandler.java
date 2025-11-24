package com.grademanagement.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for file operations
 */
public class FileHandler {

    /**
     * Read lines from a file
     */
    public static List<String> readFile(String filePath) throws IOException {
        List<String> lines = new ArrayList<>();
        File file = new File(filePath);

        // Create file if it doesn't exist
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }

        return lines;
    }

    /**
     * Write lines to a file
     */
    public static void writeFile(String filePath, List<String> lines) throws IOException {
        File file = new File(filePath);
        file.getParentFile().mkdirs();

        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            for (String line : lines) {
                writer.println(line);
            }
        }
    }

    /**
     * Append a line to a file
     */
    public static void appendFile(String filePath, String line) throws IOException {
        File file = new File(filePath);
        file.getParentFile().mkdirs();

        try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {
            writer.println(line);
        }
    }

    /**
     * Check if file exists
     */
    public static boolean fileExists(String filePath) {
        return new File(filePath).exists();
    }
}
