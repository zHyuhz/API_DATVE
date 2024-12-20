package com.DoAnChuyenNganh.UngDungDatVeXemPhim.Mapper;

import org.mapstruct.Mapper;

import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Entity.User;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.dto.request.UserRequest;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.dto.response.UserResponse;

@Mapper(componentModel = "spring")
public interface UserMapper {
	User toUser(UserRequest request);
	UserResponse toUserResponse(User user);
}
