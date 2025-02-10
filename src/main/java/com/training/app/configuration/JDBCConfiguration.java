package com.training.app.configuration;

import static com.training.app.utils.Constants.PASS_WORD;
import static com.training.app.utils.Constants.URL;
import static com.training.app.utils.Constants.USER_NAME;
import static com.training.app.utils.MessageBundle.*;

import com.training.app.exception.CustomSystemException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCConfiguration {
  private JDBCConfiguration() {
  }
  public static Connection connection() {
    try {
      return DriverManager.getConnection(URL, USER_NAME, PASS_WORD);
    } catch (SQLException e) {
      e.printStackTrace();
      throw new CustomSystemException(CONNECT_DB_FAILED);
    }
  }

  public static void initialDatabase() {
    Connection connection = null;
    PreparedStatement createDatabaseStatement = null;
    PreparedStatement useDatabaseStatement = null;
    PreparedStatement createStudentStatement = null;
    try {
      connection = connection();
      String createDatabaseSQL = "CREATE DATABASE IF NOT EXISTS training_internship;";
      String useDatabaseSQL = "USE training_internship;";
      String createStudentTableSQL = """
          CREATE TABLE IF NOT EXISTS Student (
              id INT PRIMARY KEY AUTO_INCREMENT,
              name VARCHAR(255) NOT NULL,
              gender TINYINT(1) NOT NULL CHECK (gender IN (0, 1)),
              age INT
          );
          """;
      createDatabaseStatement = connection.prepareStatement(createDatabaseSQL);
      useDatabaseStatement = connection.prepareStatement(useDatabaseSQL);
      createStudentStatement = connection.prepareStatement(createStudentTableSQL);
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (connection != null) {
          connection.close();
        }
        if (createDatabaseStatement != null) {
          createDatabaseStatement.close();
        }
        if (useDatabaseStatement != null) {
          useDatabaseStatement.close();
        }
        if (createStudentStatement != null) {
          createStudentStatement.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}

