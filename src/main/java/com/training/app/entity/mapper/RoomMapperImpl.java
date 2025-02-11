package com.training.app.entity.mapper;

import com.training.app.entity.model.Room;
import com.training.app.entity.dto.RoomDTO ;

public class RoomMapperImpl implements RoomMapper {
    @Override
    public Room toEntity(RoomDTO roomDTO){
        Room room = new Room();
        room.setRoomName(roomDTO.getRoomName());
        room.setRoomNumber(roomDTO.getRoomNumber());
        return room;
    }

}