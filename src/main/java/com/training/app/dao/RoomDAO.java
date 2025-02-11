package com.training.app.dao;
import com.training.app.entity.dto.RoomDTO;
import com.training.app.entity.dto.StudentDTO;

import java.util.List;

public interface RoomDAO {
    List<RoomDTO> showRoomDetails(int page, int size);
    List<StudentDTO> showStudentsInRoom(int roomId);
}
