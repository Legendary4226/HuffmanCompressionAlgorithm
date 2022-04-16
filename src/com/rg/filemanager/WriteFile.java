package com.rg.filemanager;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class WriteFile {
    private final String file_path;
    private FileWriter fileWriter;
    private BufferedWriter writer;

    public WriteFile(String file_path) {
        this.file_path = file_path;
        try {
            fileWriter = new FileWriter(file_path);
            writer = new BufferedWriter(fileWriter);
        } catch (Exception e) {
            System.out.println("Error opening file.");
        }
    }

    public void writeText(String text) {
        try {
            writer.write(text);
        } catch (Exception e) {
            System.out.println("Error writing a text.");
        }
    }

    public void close() {
        try {
            writer.close();
        } catch (Exception e) {
            System.out.println("Error closing file.");
        }
    }
}
