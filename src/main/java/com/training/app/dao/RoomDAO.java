package com.training.app.dao;

public interface RoomDAO {

    void exportRoomsToExcel(String filePath);

    void exportRoomsAndStudentsToExcel(String filePath);

    boolean roomExists(int roomId);
}
