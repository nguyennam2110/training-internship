package com.training.app.dao;

import static com.training.app.configuration.JDBCConfiguration.connection;

import com.training.app.entity.dto.StudentDTO;
import com.training.app.entity.mapper.StudentMapper;
import com.training.app.entity.mapper.StudentMapperImpl;
import com.training.app.entity.model.Student;

import com.training.app.utils.ExcelUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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

  @Override
  public void exportStudentsToExcel() {
    String filePath = "students.xlsx";

    try (Connection connection = connection();
         PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STUDENTS);
         ResultSet resultSet = preparedStatement.executeQuery();
         Workbook workbook = new XSSFWorkbook()) {

      Sheet sheet = workbook.createSheet("Students");
      ResultSetMetaData metaData = resultSet.getMetaData();
      int columnCount = metaData.getColumnCount();

      ExcelUtils.createHeaderRow(sheet, metaData, columnCount);
      ExcelUtils.writeDataRows(sheet, resultSet, columnCount);

      try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
        workbook.write(outputStream);
        System.out.println("Student data has been exported to students.xlsx successfully!");
      }

    } catch (IOException | SQLException e) {
      e.printStackTrace();
    }
  }

}
