package com.DoAnChuyenNganh.UngDungDatVeXemPhim.Mapper;

import org.mapstruct.Mapper;

import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Entity.Booking;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.dto.request.BookingRequest;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.dto.response.BookingReponse;

@Mapper(componentModel = "spring")
public interface BookingMapper {

	Booking toBooking(BookingRequest request);
	BookingReponse toBookingReponse(Booking booking	); 
}
