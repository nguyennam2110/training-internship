package com.training.app;

import static com.training.app.configuration.JDBCConfiguration.initialDatabase;
import static com.training.app.entity.enums.Gender.convertToValue;

import com.training.app.dao.RoomDAO;
import com.training.app.dao.RoomDAOImpl;
import com.training.app.dao.StudentDAO;
import com.training.app.dao.StudentDAOImpl;
import com.training.app.entity.dto.StudentDTO;
import com.training.app.entity.enums.Gender;
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
      System.out.println("2 - Export students to Excel");
      System.out.println("3 - Export rooms to Excel");
      System.out.println("4 - Export All");
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
          System.out.print("Enter room ID (or -1 if not in any room): ");
          Integer roomId = scanner.nextInt();
          if (roomId == -1) {
            roomId = null;
          } else {
            if (!roomDAO.roomExists(roomId)) {
              System.out.println("Error: Room ID does not exist.");
              break;
            }
          }
          StudentDTO student = new StudentDTO(name, genderEnum, age, roomId);
          studentDAO.createStudent(student);
          break;
        case 2:
          System.out.print("Enter file name (vd: students.xlsx): ");
          String filePath = scanner.nextLine();
          studentDAO.exportStudentsToExcel(filePath);
          break;
        case 3:
          System.out.print("Enter file name (ex: rooms.xlsx): ");
          String roomFilePath = scanner.nextLine();
          roomDAO.exportRoomsToExcel(roomFilePath);
          break;
        case 4:
          System.out.print("Enter file name (ex: Alldata.xlsx): ");
          String combinedFilePath = scanner.nextLine();
          roomDAO.exportRoomsAndStudentsToExcel(combinedFilePath);
          break;
        default:
          System.out.println("Goodbye!");
          return;
      }
    }
  }
}

