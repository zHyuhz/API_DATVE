package com.DoAnChuyenNganh.UngDungDatVeXemPhim.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Services.RoomService;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.dto.request.ApiResponse;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.dto.request.RoomRequest;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.dto.response.RoomResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rooms")
public class RoomController {
	@Autowired
	RoomService roomService;

	@GetMapping
	public ApiResponse<List<RoomResponse>> getAllRoom() {
		return ApiResponse.<List<RoomResponse>>builder().result(roomService.getAllRoom()).build();
	}

	@PostMapping
	public ApiResponse<RoomResponse> addRoom(@RequestBody @Valid RoomRequest request){
		return  ApiResponse.<RoomResponse>builder()
				.result(roomService.addRoom(request))
				.build();			
	}

	@DeleteMapping("/{id}")
	public ApiResponse<String>  deleteRoom(@PathVariable int id){
		return ApiResponse.<String> builder()
				.result(roomService.deleteRoom(id))
				.build();
	}
	


}
