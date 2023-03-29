package com.demo;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        Parser xlsxParser = new XlsxParser();
        String filePath = "/Users/nikki/Desktop/Data.xlsx";
        List<List<List<String>>> data = xlsxParser.parse(filePath);
        System.out.println(data);
    }
}
