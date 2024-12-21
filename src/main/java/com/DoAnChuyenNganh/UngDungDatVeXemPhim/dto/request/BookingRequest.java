package com.DoAnChuyenNganh.UngDungDatVeXemPhim.dto.request;

import java.time.LocalDate;
import java.util.List;

import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Entity.Seats;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.dto.response.SeatResponse;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingRequest {
	
	private Double price;

	private LocalDate bookingDate;
	//--
	private List<SeatResponse> seatsBooking;
	
	private int  user_id;

	private int schedule_id;
}
