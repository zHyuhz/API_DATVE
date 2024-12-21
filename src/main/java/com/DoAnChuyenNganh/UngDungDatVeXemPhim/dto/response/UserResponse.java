package com.DoAnChuyenNganh.UngDungDatVeXemPhim.dto.response;

import java.time.LocalDate;
import java.util.List;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Entity.Booking;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

	private int user_id;

	private String fullName;

	private LocalDate birthday;

	private int gender;

	private String city;

	private String phoneNumber;
	
	private AccountResponse accounts;

	private List<Booking> bookings;
}
