package com.DoAnChuyenNganh.UngDungDatVeXemPhim.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Services.UserService;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.dto.request.ApiResponse;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.dto.request.UserRequest;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.dto.response.UserResponse;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserService userService;

	@GetMapping
	public ApiResponse<List<UserResponse>> getAllUser() {
		return ApiResponse.<List<UserResponse>>builder()
				.result(userService.getAllUser())
				.build();
	}
	@DeleteMapping("/{id}")
	public ApiResponse<String> deleteUser(@PathVariable int id){
		return ApiResponse.<String>builder()
				.result(userService.deleteUser(id)) 
				.build();
	}
	@PutMapping("/{id}")
	public ApiResponse<UserResponse> updateUser(@PathVariable int id, @RequestBody UserRequest request){
		return ApiResponse.<UserResponse>builder()
				.result(userService.updateUser(id,request))
				.build();
	}
}
