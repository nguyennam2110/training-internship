package com.training.app.entity.mapper;

import com.training.app.entity.dto.RoomDTO;
import com.training.app.entity.model.Room;

import java.util.List;
import java.util.stream.Collectors;

public class RoomMapperImpl implements RoomMapper {

  @Override
  public Room toEntity(RoomDTO roomDTO) {
    Room room = new Room();
    room.setRoomName(roomDTO.getRoomName());
    room.setRoomNumber(roomDTO.getRoomNumber());
    return room;
  }
}