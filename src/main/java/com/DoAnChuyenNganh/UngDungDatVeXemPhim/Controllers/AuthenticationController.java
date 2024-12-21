package com.DoAnChuyenNganh.UngDungDatVeXemPhim.Controllers;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Services.AuthenticationService;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.dto.request.ApiResponse;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.dto.request.AuthenticationRequest;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.dto.request.IntroSpectTokenRequest;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.dto.response.AuthenticationResponse;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.dto.response.IntroSpectTokenResponse;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.KeyLengthException;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	@Autowired
	private AuthenticationService authenticationService;

	
	//Đăng nhập và tạo token
	@PostMapping("/login")
	public ApiResponse<AuthenticationResponse> authenticate (@RequestBody AuthenticationRequest request) throws KeyLengthException, JOSEException {
		var result = authenticationService.Authenticate(request);
		return ApiResponse.<AuthenticationResponse>builder()
				.message("Dang nhap thanh cong ")
				.result(null)
				.build();
	}
	
	//Xác thực token
	@PostMapping("/introspect")
	public ApiResponse<IntroSpectTokenResponse> authenticate (@RequestBody IntroSpectTokenRequest request) throws KeyLengthException, JOSEException, ParseException {
		var result = authenticationService.introspect(request);
		return ApiResponse.<IntroSpectTokenResponse>builder()
				.result(result)
				.build();
	}
}
