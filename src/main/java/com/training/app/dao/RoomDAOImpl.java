package com.training.app.dao;

import com.training.app.configuration.JDBCConfiguration;
import com.training.app.entity.dto.RoomDTO;
import com.training.app.entity.dto.StudentDTO;
import com.training.app.entity.enums.Gender;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDAOImpl implements RoomDAO {


    @Override
    public List<RoomDTO> showRoomDetails(int page, int size) {
        List<RoomDTO> rooms = new ArrayList<>();
        String query = """
        SELECT r.id, r.roomName, r.roomNumber, COUNT(s.id) AS studentCount
        FROM training_internship.Room r
        LEFT JOIN training_internship.Student s ON r.id = s.roomId
        GROUP BY r.id, r.roomName, r.roomNumber
        LIMIT ?, ?;
    """;
        try (Connection connection = JDBCConfiguration.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            int offset = (page - 1) * size;
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, size);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String roomName = resultSet.getString("roomName");
                    String roomNumber = resultSet.getString("roomNumber");
                    int studentCount = resultSet.getInt("studentCount");

                    rooms.add(new RoomDTO(id, roomName, roomNumber, studentCount));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    @Override
    public List<StudentDTO> showStudentsInRoom(int roomId) {
        List<StudentDTO> students = new ArrayList<>();
        String query = """
        SELECT s.name, s.gender, s.age
        FROM training_internship.Student s
        WHERE s.roomId = ?;
    """;
        try (Connection connection = JDBCConfiguration.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, roomId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    int genderValue = resultSet.getInt("gender"); // Lấy giá trị số từ DB
                    int age = resultSet.getInt("age");

                    // Chuyển đổi từ số sang Enum
                    Gender gender = Gender.values()[genderValue];

                    students.add(new StudentDTO(name, gender, age));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }



}

