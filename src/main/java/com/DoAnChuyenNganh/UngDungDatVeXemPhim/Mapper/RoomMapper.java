package com.DoAnChuyenNganh.UngDungDatVeXemPhim.Mapper;

import org.mapstruct.Mapper;

import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Entity.Room;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.dto.response.RoomResponse;

import resquest.RoomRequest;
@Mapper(componentModel = "spring")
public interface RoomMapper {
	Room toRoom(RoomRequest request);
	RoomResponse toRoomResponse(Room room);
}
