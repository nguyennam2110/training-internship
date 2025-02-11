package com.training.app.dao;

public interface RoomDAO {

  void exportRoomsToExcel();

  void exportRoomsAndStudentsToExcel();

  boolean isRoomExists(int roomId);
}
