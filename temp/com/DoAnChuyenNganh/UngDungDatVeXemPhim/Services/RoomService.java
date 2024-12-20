package com.DoAnChuyenNganh.UngDungDatVeXemPhim.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Entity.Room;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Mapper.RoomMapper;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Repo.RoomRepo;

import com.DoAnChuyenNganh.UngDungDatVeXemPhim.dto.request.RoomRequest;

import com.DoAnChuyenNganh.UngDungDatVeXemPhim.dto.response.RoomResponse;

@Service
public class RoomService {
	@Autowired
	RoomRepo roomRepo;
	@Autowired
	RoomMapper roomMapper;

	//getAllRoom
	public List<RoomResponse> getAllRoom() {
		List<Room>list =  roomRepo.findAll();
		
		return list.stream()
				.map(roomMapper::toRoomResponse)
				.toList();
	}

	// addRoom
	public RoomResponse addRoom(RoomRequest request) {
		Room room = roomMapper.toRoom(request);
		return roomMapper.toRoomResponse(roomRepo.save(room));
	}
	//deleteRoom
	public String deleteRoom(int id) {
		roomRepo.deleteById(id);
		return "Xoa thanh cong room: " + String.valueOf(id);
	}

}
