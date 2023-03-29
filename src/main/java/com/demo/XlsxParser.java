package com.demo;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class XlsxParser {
    public static void main(String[] args) {
        String filePath = "/Users/nikki/Desktop/Data.xlsx";

        try (InputStream inputStream = new FileInputStream(filePath)) {
            Workbook workbook = WorkbookFactory.create(inputStream);
            List<List<List<String>>> data = parseWorkbook(workbook);
            System.out.println(data);
        } catch (Exception e) {
            System.out.println("Could not process file " + filePath);
        }
    }

    private static List<List<List<String>>> parseWorkbook(Workbook workbook) {
        List<List<List<String>>> data = new ArrayList<>();
        workbook.iterator().forEachRemaining(sheet -> data.add(parseSheet(sheet)));
        return data;
    }

    private static List<List<String>> parseSheet(Sheet sheet) {
        List<List<String>> data = new ArrayList<>();
        sheet.iterator().forEachRemaining(row -> data.add(parseRow(row)));
        return data;
    }

    private static List<String> parseRow(Row row) {
        List<String> data = new ArrayList<>();
        row.iterator().forEachRemaining(cell -> data.add(parseCell(cell)));
        return data;
    }

    private static String parseCell(Cell cell) {
        return switch (cell.getCellType()) {
            case NUMERIC -> String.valueOf(new DataFormatter().formatCellValue(cell));
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            case STRING, FORMULA -> cell.getStringCellValue();
            case BLANK, ERROR, _NONE -> "";
        };
    }
}
