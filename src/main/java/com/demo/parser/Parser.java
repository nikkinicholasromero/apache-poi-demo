package com.demo.parser;

import java.util.List;

public class Parser {
    private final List<FileParser> fileParserList = List.of(new XlsxParser(), new CsvParser());

    public List<List<List<String>>> parse(String filePath) {
        for (FileParser fileParser : fileParserList) {
            if (fileParser.isSupported(filePath)) {
                return fileParser.parse(filePath);
            }
        }

        throw new IllegalArgumentException("File type not supported");
    }
}
