package com.training.app.dao;

import static com.training.app.configuration.JDBCConfiguration.connection;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomDAOImpl implements RoomDAO {

  private static final String SELECT_ALL_ROOMS = "SELECT * FROM training_internship.Room";
  private static final String SELECT_ROOMS_AND_STUDENTS =
      "SELECT s.name AS student_name, s.gender, s.age, "
          + "r.id AS room_id, r.roomName, r.roomNumber " + "FROM training_internship.Student s "
          + "LEFT JOIN training_internship.Room r ON s.roomId = r.id";

  public void exportRoomsToExcel(String filePath) {
    try (Connection connection = connection(); PreparedStatement preparedStatement = connection.prepareStatement(
        SELECT_ALL_ROOMS); ResultSet resultSet = preparedStatement.executeQuery(); Workbook workbook = new XSSFWorkbook()) {

      Sheet sheet = workbook.createSheet("Rooms");

      Row headerRow = sheet.createRow(0);
      headerRow.createCell(0).setCellValue("ID");
      headerRow.createCell(1).setCellValue("Room Name");
      headerRow.createCell(2).setCellValue("Room Number");

      int rowIndex = 1;
      while (resultSet.next()) {
        Row row = sheet.createRow(rowIndex++);
        row.createCell(0).setCellValue(resultSet.getInt("id"));
        row.createCell(1).setCellValue(resultSet.getString("roomName"));
        row.createCell(2).setCellValue(resultSet.getInt("roomNumber"));
      }

      try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
        workbook.write(outputStream);
        System.out.println("Room data has been exported to Excel successfully!");
      }

    } catch (IOException e) {
      System.err.println("Error writing Excel file: " + e.getMessage());
    } catch (SQLException e) {
      System.err.println("SQL Error: " + e.getMessage());
    }
  }

  public void exportRoomsAndStudentsToExcel(String filePath) {
    try (Connection connection = connection(); PreparedStatement preparedStatement = connection.prepareStatement(
        SELECT_ROOMS_AND_STUDENTS); ResultSet resultSet = preparedStatement.executeQuery(); Workbook workbook = new XSSFWorkbook()) {
      Sheet sheet = workbook.createSheet("Rooms and Students");
      Row headerRow = sheet.createRow(0);
      String[] headers = {"Name", "Gender", "Age", "Room ID", "Room Name", "Room Number"};
      for (int i = 0; i < headers.length; i++) {
        headerRow.createCell(i).setCellValue(headers[i]);
      }
      int rowIndex = 1;
      while (resultSet.next()) {
        Row row = sheet.createRow(rowIndex++);
        row.createCell(0).setCellValue(resultSet.getString("student_name"));
        row.createCell(1).setCellValue(resultSet.getString("gender"));
        row.createCell(2).setCellValue(resultSet.getInt("age"));
        row.createCell(3).setCellValue(resultSet.getInt("room_id"));
        row.createCell(4).setCellValue(resultSet.getString("roomName"));
        row.createCell(5).setCellValue(resultSet.getInt("roomNumber"));
      }

      // Ghi vào tệp Excel
      try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
        workbook.write(outputStream);
        System.out.println("Room and student data has been exported to Excel successfully!");
      }

    } catch (IOException e) {
      System.err.println("Error writing Excel file: " + e.getMessage());
    } catch (SQLException e) {
      System.err.println("SQL Error: " + e.getMessage());
    }
  }

  public boolean isroomExists(int roomId) {
    String query = "SELECT COUNT(*) FROM training_internship.Room WHERE id = ?";
    try (Connection connection = connection(); PreparedStatement preparedStatement = connection.prepareStatement(
        query)) {
      preparedStatement.setInt(1, roomId);
      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        return resultSet.getInt(1) > 0;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

}