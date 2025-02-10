package com.training.app.entity.model;

import java.util.List;

public class Room {
    private Integer id;
    private String roomName;
    private Integer roomNumber;
    private List<Student> students;

    public Room() {
    }

    public Room(Integer id, String roomName, Integer roomNumber, List<Student> students) {
        this.id = id;
        this.roomName = roomName;
        this.roomNumber = roomNumber;
        this.students = students;
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getRoomName() { return roomName; }

    public void setRoomName(String roomName) { this.roomName = roomName; }

    public Integer getRoomNumber() { return roomNumber; }

    public void setRoomNumber(Integer roomNumber) { this.roomNumber = roomNumber; }

    public List<Student> getStudents() { return students; }

    public void setStudents(List<Student> students) { this.students = students; }
}