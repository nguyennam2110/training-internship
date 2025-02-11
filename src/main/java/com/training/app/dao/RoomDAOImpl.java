package com.training.app.dao;

import static com.training.app.configuration.JDBCConfiguration.connection;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.training.app.utils.ExcelUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class RoomDAOImpl implements RoomDAO {

  private static final String SELECT_ALL_ROOMS = "SELECT * FROM training_internship.Room";
  private static final String SELECT_ROOMS_AND_STUDENTS =
      "SELECT s.name AS student_name, s.gender, s.age, "
          + "r.id AS room_id, r.roomName, r.roomNumber " + "FROM training_internship.Student s "
          + "LEFT JOIN training_internship.Room r ON s.roomId = r.id";

  @Override
  public void exportRoomsToExcel() {
    String filePath = "rooms.xlsx";

    try (Connection connection = connection();
         PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ROOMS);
         ResultSet resultSet = preparedStatement.executeQuery();
         Workbook workbook = new XSSFWorkbook()) {

      Sheet sheet = workbook.createSheet("Rooms");
      ResultSetMetaData metaData = resultSet.getMetaData();
      int columnCount = metaData.getColumnCount();

      ExcelUtils.createHeaderRow(sheet, metaData, columnCount);
      ExcelUtils.writeDataRows(sheet, resultSet, columnCount);

      try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
        workbook.write(outputStream);
        System.out.println("Room data has been exported to rooms.xlsx successfully!");
      }

    } catch (IOException e) {
      System.err.println("Error writing Excel file: " + e.getMessage());
    } catch (SQLException e) {
      System.err.println("SQL Error: " + e.getMessage());
    }
  }


  @Override
  public void exportRoomsAndStudentsToExcel() {
    String filePath = "All.xlsx";

    try (Connection connection = connection();
         PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ROOMS_AND_STUDENTS);
         ResultSet resultSet = preparedStatement.executeQuery();
         Workbook workbook = new XSSFWorkbook()) {

      Sheet sheet = workbook.createSheet("Rooms and Students");
      ResultSetMetaData metaData = resultSet.getMetaData();
      int columnCount = metaData.getColumnCount();

      ExcelUtils.createHeaderRow(sheet, metaData, columnCount);
      ExcelUtils.writeDataRows(sheet, resultSet, columnCount);

      try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
        workbook.write(outputStream);
        System.out.println("Room and student data has been exported to rooms_and_students.xlsx successfully!");
      }

    } catch (IOException e) {
      System.err.println("Error writing Excel file: " + e.getMessage());
    } catch (SQLException e) {
      System.err.println("SQL Error: " + e.getMessage());
    }
  }


  public boolean isRoomExists(int roomId) {
    String query = "SELECT 1 FROM training_internship.Room WHERE id = ? LIMIT 1";
    try (Connection connection = connection();
         PreparedStatement preparedStatement = connection.prepareStatement(query)) {

      preparedStatement.setInt(1, roomId);
      ResultSet resultSet = preparedStatement.executeQuery();
      return resultSet.next();

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

}