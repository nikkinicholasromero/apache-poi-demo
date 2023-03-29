package com.demo;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class XlsxParser implements Parser {
    @Override
    public List<List<List<String>>> parse(String filePath) {
        try (InputStream inputStream = new FileInputStream(filePath)) {
            Workbook workbook = WorkbookFactory.create(inputStream);
            return parseWorkbook(workbook);
        } catch (Exception e) {
            System.out.println("Could not process file " + filePath);
            throw new RuntimeException(e);
        }
    }

    private List<List<List<String>>> parseWorkbook(Workbook workbook) {
        List<List<List<String>>> data = new ArrayList<>();
        workbook.iterator().forEachRemaining(sheet -> data.add(parseSheet(sheet)));
        return data;
    }

    private List<List<String>> parseSheet(Sheet sheet) {
        List<List<String>> data = new ArrayList<>();
        sheet.iterator().forEachRemaining(row -> data.add(parseRow(row)));
        return data;
    }

    private List<String> parseRow(Row row) {
        List<String> data = new ArrayList<>();
        row.iterator().forEachRemaining(cell -> data.add(parseCell(cell)));
        return data;
    }

    private String parseCell(Cell cell) {
        return String.valueOf(new DataFormatter().formatCellValue(cell));
    }
}
