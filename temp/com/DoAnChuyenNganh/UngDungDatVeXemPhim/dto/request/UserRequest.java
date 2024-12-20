package com.DoAnChuyenNganh.UngDungDatVeXemPhim.dto.request;

import java.time.LocalDate;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {
	
	private String fullName;

	private LocalDate birthday;

	private int gender;
	
	private String city;

	private String phoneNumber;

	//private int account_id;
	
}
