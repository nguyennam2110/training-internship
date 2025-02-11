package com.training.app.entity.model;

import com.training.app.entity.dto.StudentDTO;

import java.util.ArrayList;
import java.util.List;

import java.util.Map;

public class Room {
    private Integer id;
    private String roomName;
    private String roomNumber;
    private List<StudentDTO> students;

    public Room() {
    }
    public Room(Integer id, String roomName, String roomNumber) {
        this.id = id;
        this.roomName = roomName;
        this.roomNumber = roomNumber;
        this.students = new ArrayList<>();
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getRoomName() {
        return roomName;
    }
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
    public String getRoomNumber() {
        return roomNumber;
    }
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }


    public List<StudentDTO> getStudents() {
        return students;
    }

    public void addStudent(StudentDTO student) {
        this.students.add(student);
    }
}