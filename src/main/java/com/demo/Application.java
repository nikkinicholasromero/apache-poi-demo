package com.demo;

import com.demo.parser.Parser;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        List<List<List<String>>> data = new Parser().parse("/Users/nikki/Desktop/Data.xlsx");
        System.out.println(data);
    }
}
