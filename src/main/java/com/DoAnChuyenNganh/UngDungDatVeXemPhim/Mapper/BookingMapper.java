package com.DoAnChuyenNganh.UngDungDatVeXemPhim.Mapper;

import org.mapstruct.Mapper;

import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Entity.Booking;

import Response.BookingReponse;
import resquest.BookingRequest;

@Mapper(componentModel = "spring")
public interface BookingMapper {

	Booking toBooking(BookingRequest request);
	BookingReponse toBookingReponse(Booking booking	); 
}
