package com.urise.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainFile {
    public static void main(String[] args) {
        String filePath = ".\\.gitignore";

        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }

        File dir = new File("./src");
        System.out.println(dir.isDirectory());
        String[] list = dir.list();
        if (list != null) {
            for (String name : list) {
                System.out.println(name);
            }
        }

        try (FileInputStream fis = new FileInputStream(filePath)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println();

        showContentDirectory(dir, "");
    }

    public static void showContentDirectory(File dir, String symbol) {
        if (dir.isDirectory()) {
            System.out.println(symbol + "D: " + dir.getName());
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file: files) {
                    showContentDirectory(file, symbol + "\t");
                }
            }
        } else {
            System.out.println(symbol + "F: " + "\t" + dir.getName());
        }
    }
}
