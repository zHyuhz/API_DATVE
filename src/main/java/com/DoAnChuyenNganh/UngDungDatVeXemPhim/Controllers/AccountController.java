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

import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Entity.Accounts;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Services.AccountService;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.dto.response.AccountResponse;

import jakarta.validation.Valid;
import resquest.ApiResponse;
import resquest.RegisterAccountRequest;
import resquest.UpdateAccountRequest;

@RestController
@RequestMapping("/accounts")
public class AccountController {
	@Autowired
	private AccountService accountService;

	@GetMapping
	public ApiResponse<List<AccountResponse>> getAllAccount() {
		return ApiResponse.<List<AccountResponse>>builder()
				.result(accountService.getAllAccount())
				.build();
	}

	@GetMapping("/{userName}")
	public ApiResponse<AccountResponse> getAccount(@PathVariable String userName) {
		return ApiResponse.<AccountResponse>builder()
				.result(accountService.getAccount(userName))
				.build();
	}

	@PostMapping("/register")
	public ApiResponse<AccountResponse> registerAccount(@RequestBody @Valid RegisterAccountRequest request) {
		return ApiResponse.<AccountResponse>builder()
				.result(accountService.createAccount(request))
				.build();
	}

	@PutMapping("/{userName}")
	public ApiResponse<AccountResponse> updateAccount(@PathVariable @Valid String userName, @RequestBody UpdateAccountRequest request) {
		return ApiResponse.<AccountResponse>builder()
				.result(accountService.updateAccount(userName, request))
				.build();
	}

	@DeleteMapping("/{userName}")
	public ApiResponse<String> deleteAccount(@PathVariable String userName) {
		accountService.deleteAccount(userName);
		return ApiResponse.<String>builder()
				.result("Xoa " + userName + " thanh cong!!")
				.build()  ;
	}
	
	
}
