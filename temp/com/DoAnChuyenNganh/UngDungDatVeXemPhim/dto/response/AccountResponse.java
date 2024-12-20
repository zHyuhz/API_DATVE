package com.DoAnChuyenNganh.UngDungDatVeXemPhim.dto.response;

import java.time.LocalDateTime;

import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountResponse {
	
	private int account_id;

	private String userName;

	private String email;
	
	private LocalDateTime createdAt;

	private int status;

	private String accountRole;
	
	private User user;
}
