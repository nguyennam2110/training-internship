package com.training.app;

import static com.training.app.configuration.JDBCConfiguration.initialDatabase;
import static com.training.app.entity.enums.Gender.convertToValue;

import com.training.app.dao.StudentDAO;
import com.training.app.dao.StudentDAOImpl;
import com.training.app.entity.dto.StudentDTO;
import com.training.app.entity.enums.Gender;
import com.training.app.entity.model.Student;

import java.util.Scanner;

public class MainApplication {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    initialDatabase();
    StudentDAO studentDAO = new StudentDAOImpl();
    while (true) {
      System.out.println("Please choose an action:");
      System.out.println("1 - Create new student");
      System.out.println("2 - Search for a student");
      System.out.println("0 - Exit");

      int selection = scanner.nextInt();
      scanner.nextLine();

      switch (selection) {
        case 1:
          System.out.print("Enter student name: ");
          String name = scanner.nextLine();
          System.out.print("Enter gender: ");
          System.out.println("Please choose 1: Male or 0: Female");
          int gender = scanner.nextInt();
          Gender genderEnum = convertToValue(gender);
          System.out.print("Enter age: ");
          int age = scanner.nextInt();
          StudentDTO student = new StudentDTO(name, genderEnum, age);
          studentDAO.createStudent(student);
          break;

        case 2:
          System.out.print("Enter student name to search: ");
          String searchName = scanner.nextLine();
          Student foundStudent = studentDAO.searchStudent(new StudentDTO(searchName, null, 0));

          if (foundStudent != null) {
            System.out.println("Student found: " + foundStudent.getName() + ", Gender: " + foundStudent.getGender() + ", Age: " + foundStudent.getAge());
          } else {
            System.out.println("Student not found.");
          }
          break;1

        default:
          System.out.println("Goodbye!");
          return;
      }
    }
  }
}