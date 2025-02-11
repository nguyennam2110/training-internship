package com.training.app.dao;

import static com.training.app.configuration.JDBCConfiguration.connection;

import com.training.app.entity.dto.StudentDTO;
import com.training.app.entity.mapper.StudentMapper;
import com.training.app.entity.mapper.StudentMapperImpl;
import com.training.app.entity.model.Student;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDAOImpl implements StudentDAO {

  private static final String INSERT_STUDENT = "INSERT INTO training_internship.Student(name, gender, age, roomId) VALUES (?, ?, ?, ?)";
  private static final String SELECT_ALL_STUDENTS = "SELECT * FROM training_internship.Student";
  private final StudentMapper studentMapper = new StudentMapperImpl();

  @Override
  public int createStudent(StudentDTO studentRequest) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
      connection = connection();
      preparedStatement = connection.prepareStatement(INSERT_STUDENT);

      Student student = studentMapper.toEntity(studentRequest);
      preparedStatement.setString(1, student.getName());
      preparedStatement.setInt(2, studentRequest.getGender().getCode());
      preparedStatement.setInt(3, student.getAge());
      preparedStatement.setObject(4, studentRequest.getRoomId(), java.sql.Types.INTEGER);
      int result = preparedStatement.executeUpdate();
      System.out.println("Student has been created successfully.");
      return result;
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Error: " + e.getMessage());
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
        if (connection != null) {
          connection.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return 0;
  }

  public void exportStudentsToExcel(String filePath) {
    try (Connection connection = connection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STUDENTS);
        ResultSet resultSet = preparedStatement.executeQuery();
        Workbook workbook = new XSSFWorkbook()) {

      Sheet sheet = workbook.createSheet("Students");
      String[] headers = {"ID", "Name", "Gender", "Age"};

      createHeaderRow(sheet, headers);

      int rowIndex = 1;
      while (resultSet.next()) {
        Row row = sheet.createRow(rowIndex++);
        row.createCell(0).setCellValue(resultSet.getInt("id"));
        row.createCell(1).setCellValue(resultSet.getString("name"));
        row.createCell(2).setCellValue(resultSet.getInt("gender") == 1 ? "Male" : "Female");
        row.createCell(3).setCellValue(resultSet.getInt("age"));
      }

      try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
        workbook.write(outputStream);
        System.out.println("Student data has been exported to Excel successfully!");
      }
    } catch (IOException | SQLException e) {
      e.printStackTrace();
    }
  }

  private void createHeaderRow(Sheet sheet, String[] headers) {
    Row headerRow = sheet.createRow(0);
    for (int i = 0; i < headers.length; i++) {
      headerRow.createCell(i).setCellValue(headers[i]);
    }

  }

}
