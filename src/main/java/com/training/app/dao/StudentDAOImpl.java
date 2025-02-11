package com.training.app.dao;

import static com.training.app.configuration.JDBCConfiguration.connection;

import com.training.app.entity.enums.Gender;
import com.training.app.entity.dto.StudentDTO;
import com.training.app.entity.mapper.StudentMapper;
import com.training.app.entity.mapper.StudentMapperImpl;
import com.training.app.entity.model.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class StudentDAOImpl implements StudentDAO {

  private static final String INSERT_STUDENT = "INSERT INTO training_internship.Student(name,gender,age) VALUES (?,?,?)";
  private static final String SEARCH_STUDENT = "SELECT * FROM training_internship.Student WHERE name=?";
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

  public Student searchStudent(StudentDTO studentDTO) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    try {
      connection = connection();
      preparedStatement = connection.prepareStatement(SEARCH_STUDENT);
      preparedStatement.setString(1, studentDTO.getName());

      resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        Student student = new Student();
        student.setId(resultSet.getInt("id"));
        student.setName(resultSet.getString("name"));
        student.setGender(Gender.values()[resultSet.getInt("gender")]);
        student.setAge(resultSet.getInt("age"));
        return student;
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Error: " + e.getMessage());
    } finally {
      try {
        if (resultSet != null) {
          resultSet.close();
        }
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
    return null;
  }




}
