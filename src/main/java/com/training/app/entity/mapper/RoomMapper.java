package com.training.app.entity.mapper;

import com.training.app.entity.dto.RoomDTO;
import com.training.app.entity.model.Room;

public interface RoomMapper {

    Room toEntity(RoomDTO roomDTO);

}
