package com.rg.filemanager;

import java.io.BufferedReader;
import java.io.FileReader;

public class ReadFile {
    private final String file_path;
    private FileReader filereader;
    private BufferedReader reader;

    public ReadFile(String path) {
        file_path = path;
        try {
            filereader = new FileReader(path);
            reader = new BufferedReader(filereader);
        } catch(Exception e) {
            System.out.println("Error opening file.");
            System.exit(-1);
        }
    }

    public String getFile_path() {
        return file_path;
    }

    public String getLine() {
        String get = null;
        try {
            get = reader.readLine();
        } catch (Exception e) {
            System.out.println("Error reading a line.");
            System.exit(-1);
        }
        return get;
    }

    public void close() {
        try {
            reader.close();
        } catch (Exception e) {
            System.out.println("Error closing file.");
            System.exit(-1);
        }
    }
}
