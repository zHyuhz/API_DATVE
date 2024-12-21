package com.DoAnChuyenNganh.UngDungDatVeXemPhim.Mapper;

import org.mapstruct.Mapper;

import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Entity.Room;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.dto.request.RoomRequest;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.dto.response.RoomResponse;
@Mapper(componentModel = "spring")
public interface RoomMapper {
	Room toRoom(RoomRequest request);
	RoomResponse toRoomResponse(Room room);
}
