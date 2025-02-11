package com.training.app;

import static com.training.app.configuration.JDBCConfiguration.initialDatabase;
import static com.training.app.entity.enums.Gender.convertToValue;

import com.training.app.configuration.JDBCConfiguration;
import com.training.app.dao.RoomDAO;
import com.training.app.dao.RoomDAOImpl;
import com.training.app.dao.StudentDAO;
import com.training.app.dao.StudentDAOImpl;
import com.training.app.entity.dto.RoomDTO;
import com.training.app.entity.dto.StudentDTO;
import com.training.app.entity.enums.Gender;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

public class MainApplication {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    initialDatabase();
    StudentDAO studentDAO = new StudentDAOImpl();
    RoomDAO roomDAO = new RoomDAOImpl();
    while (true) {
      System.out.println("Please choose an action:");
      System.out.println("1 - Create new student");
      System.out.println("3 - Show room details");
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
        case 3:
          System.out.print("Enter page number: ");
          int page = scanner.nextInt();
          System.out.print("Enter page size: ");
          int size = scanner.nextInt();

          List<RoomDTO> rooms = roomDAO.showRoomDetails(page, size);

          System.out.println("ID | Room Name | Room Number | Student Count");
          if (rooms.isEmpty()) {
            System.out.println("⚠️ Không có dữ liệu trong khoảng này.");
          } else {
            for (RoomDTO room : rooms) {
              System.out.println(room.getId() + " | " + room.getRoomName() + " | " + room.getRoomNumber() + " | " + room.getStudentCount());
            }
          }

          System.out.print("Nhập mã phòng để hiển thị chi tiết sinh viên: ");
          int roomId = scanner.nextInt();

          List<StudentDTO> students = roomDAO.showStudentsInRoom(roomId);

          System.out.println("\nSinh viên trong Room ID " + roomId);
          System.out.println("Name | Gender | Age");

          if (students.isEmpty()) {
            System.out.println("⚠️ Không có sinh viên trong phòng này.");
          } else {
            for (StudentDTO s : students) {
              System.out.println(s.getName() + " | " + s.getGender() + " | " + s.getAge());
            }
          }

          break;


        case 0:
          System.out.println("Goodbye!");
          return;

        default:
          System.out.println("Invalid selection, please try again.");
      }
    }
  }
}
