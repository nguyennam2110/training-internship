package com.training.app.utils;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ExcelUtils {

    public static void createHeaderRow(Sheet sheet, ResultSetMetaData metaData, int columnCount) throws SQLException {
        Row headerRow = sheet.createRow(0);
        for (int i = 1; i <= columnCount; i++) {
            headerRow.createCell(i - 1).setCellValue(metaData.getColumnLabel(i));
        }
    }

    public static void writeDataRows(Sheet sheet, ResultSet resultSet, int columnCount) throws SQLException {
        int rowIndex = 1;
        while (resultSet.next()) {
            Row row = sheet.createRow(rowIndex++);
            for (int i = 1; i <= columnCount; i++) {
                row.createCell(i - 1).setCellValue(resultSet.getString(i));
            }
        }
    }
}
