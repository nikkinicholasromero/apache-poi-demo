package com.demo.parser;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FilenameUtils;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

class CsvParser implements FileParser {
    private final List<String> supportedFileTypes = List.of("csv");

    @Override
    public boolean isSupported(String filePath) {
        String fileExtension = FilenameUtils.getExtension(filePath);
        return supportedFileTypes.contains(fileExtension.toLowerCase());
    }

    @Override
    public List<List<List<String>>> parse(String filePath) {
        try {
            Reader in = new FileReader(filePath);
            CSVFormat csvFormat = CSVFormat.DEFAULT;
            CSVParser parser = csvFormat.parse(in);
            List<List<String>> sheet = new ArrayList<>();
            for (CSVRecord csvRecord : parser) {
                List<String> row = new ArrayList<>();
                for (int i = 0; i < csvRecord.size(); i++) {
                    row.add(csvRecord.get(i));
                }
                sheet.add(row);
            }
            return List.of(sheet);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
