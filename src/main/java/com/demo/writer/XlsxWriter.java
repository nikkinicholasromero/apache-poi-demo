package com.demo.writer;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class XlsxWriter {
    private static final String TARGET_PATH = System.getProperty("java.io.tmpdir") + System.lineSeparator() + "%s.xlsx";
    private static final String SHEET_NAME = "Sheet1";

    public File write(String fileName, Object[][] data) {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet(SHEET_NAME);

            int rowCount = 0;

            for (Object[] rowData : data) {
                Row row = sheet.createRow(rowCount++);

                int columnCount = 0;

                for (Object field : rowData) {
                    Cell cell = row.createCell(columnCount++);
                    if (field instanceof String) {
                        cell.setCellValue((String) field);
                    } else if (field instanceof Integer) {
                        cell.setCellValue((Integer) field);
                    }
                }
            }

            String targetPath = TARGET_PATH.formatted(fileName);
            try (FileOutputStream outputStream = new FileOutputStream(targetPath)) {
                workbook.write(outputStream);
            }

            return new File(targetPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
