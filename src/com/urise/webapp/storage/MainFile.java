package com.urise.webapp.storage;

import java.io.File;

public class MainFile {
    public static void main(String[] args) {
        File file = new File(".gitignore");
        System.out.println(file.getAbsoluteFile());
    }
}
