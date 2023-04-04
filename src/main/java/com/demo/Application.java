package com.demo;

import com.demo.writer.XlsxWriter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

public class Application {
    public static void main(String[] args) throws IOException {
        String fileName = UUID.randomUUID().toString();

        Object[][] data = {
                {"Head First Java", "Kathy Serria", 79},
                {"Effective Java", "Joshua Bloch", 36},
                {"Clean Code", "Robert martin", 42},
                {"Thinking in Java", "Bruce Eckel", 35},
        };

        XlsxWriter xlsxWriter = new XlsxWriter();
        File file = xlsxWriter.write(fileName, data);
        Files.move(file.toPath(), new File("/Users/nikki/Desktop/someData.xlsx").toPath());
    }
}
