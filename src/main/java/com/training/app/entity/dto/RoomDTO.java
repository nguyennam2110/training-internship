package com.training.app.entity.dto;

public class RoomDTO {
    private Integer id;
    private String roomName;
    private String roomNumber;
    private Integer studentCount;

    public RoomDTO(int id, String roomName, String roomNumber, int studentCount) {
        this.id = id;
        this.roomName = roomName;
        this.roomNumber = roomNumber;
        this.studentCount = studentCount;
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

    public Integer getStudentCount() {
        return studentCount;
    }
    public void setStudentCount(Integer studentCount) {
        this.studentCount = studentCount;
    }
}
