package com.resume.webapp;

import java.io.File;

public class MainFile {
    public static void main(String[] args) {
        File file = new File(".");
        MainFile.printСontent(file, "");
    }

    public static void printСontent(File file, String indent) {
        System.out.println(indent + file.getName() + "{");
        indent += "-  ";
        File[] list = file.listFiles();
        for (File name : list) {
            if (name.isDirectory()) {
                printСontent(name, indent);
            } else {
                System.out.println(indent + name);
            }
        }
        System.out.println(indent + "}");
    }
}
