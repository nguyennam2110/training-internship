package com.training.app.dao;

import static com.training.app.configuration.JDBCConfiguration.connection;

import com.training.app.entity.dto.StudentDTO;
import com.training.app.entity.mapper.StudentMapper;
import com.training.app.entity.mapper.StudentMapperImpl;
import com.training.app.entity.model.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDAOImpl implements StudentDAO {

  private static final String INSERT_STUDENT = "INSERT INTO training_internship.Student(name,gender,age) VALUES (?,?,?)";
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
      int result =  preparedStatement.executeUpdate();
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


}
