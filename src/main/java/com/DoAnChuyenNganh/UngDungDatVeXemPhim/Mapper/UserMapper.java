package com.DoAnChuyenNganh.UngDungDatVeXemPhim.Mapper;

import org.mapstruct.Mapper;

import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Entity.User;

import Response.UserResponse;
import resquest.UserRequest;

@Mapper(componentModel = "spring")
public interface UserMapper {
	User toUser(UserRequest request);
	UserResponse toUserResponse(User user);
}
