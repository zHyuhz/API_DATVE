package com.DoAnChuyenNganh.UngDungDatVeXemPhim.Exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.DoAnChuyenNganh.UngDungDatVeXemPhim.dto.request.ApiResponse;

//Khi 1 Exception xảy ra thì class này sẽ chịu nhiệm vụ xử lý Exception 
@ControllerAdvice

public class GlobalExceptionHandle {

	// Xử lý lỗi RuntimeException
	// Sử dụng @ExceptionHandler và Exception muốn xử lý

		@ExceptionHandler(value = Exception.class)
		ResponseEntity<ApiResponse> handleRuntimeException(Exception exception){
		
		ApiResponse apiResponse = new ApiResponse<>();
		apiResponse.setCode(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode());
		apiResponse.setMessage(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage());
		
			//Trả về nội dung cho client
		return ResponseEntity.badRequest().body(apiResponse);
	}

	@ExceptionHandler(value = AppException.class)
	ResponseEntity<ApiResponse> handleRuntimeException(AppException exception) {

		ErrorCode errorCode = exception.getErrorCode();

		ApiResponse apiResponse = new ApiResponse<>();
		apiResponse.setCode(errorCode.getCode());
		apiResponse.setMessage(errorCode.getMessage());

		return ResponseEntity.badRequest().body(apiResponse);
	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	ResponseEntity<ApiResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
		
		ErrorCode errorCode = ErrorCode.UNCATEGORIZED_EXCEPTION;
		try {
		errorCode =  ErrorCode.valueOf(exception.getFieldError().getDefaultMessage());
		}catch (Exception e) {
			
		}
		ApiResponse apiResponse = new ApiResponse<>();
		apiResponse.setCode(errorCode.getCode());
		apiResponse.setMessage(errorCode.getMessage());

		return ResponseEntity.badRequest().body(apiResponse);
	}
}
