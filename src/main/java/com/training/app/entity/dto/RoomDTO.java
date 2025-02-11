package com.training.app.entity.dto;

import java.util.List;

public class RoomDTO {

    private String roomName;
    private Integer roomNumber;
    private List<Integer> studentIds;

    public RoomDTO() {
    }

    public RoomDTO(String roomName, Integer roomNumber, List<Integer> studentIds) {
        this.roomName = roomName;
        this.roomNumber = roomNumber;
        this.studentIds = studentIds;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public List<Integer> getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(List<Integer> studentIds) {
        this.studentIds = studentIds;
    }
}
