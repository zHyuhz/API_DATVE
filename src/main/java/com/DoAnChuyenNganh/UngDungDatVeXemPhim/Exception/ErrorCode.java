package com.DoAnChuyenNganh.UngDungDatVeXemPhim.Exception;

public enum ErrorCode {
	UNCATEGORIZED_EXCEPTION(400,"Khong xac dinh"),
	ACCOUNT_USERNAME_EXISTED(401,"Username da ton tai"),
	ACCOUNT_EMAIL_EXISTED(402,"Email da ton tai"),
	ACCOUNT_NOT_FOUND(403,"Khong tim thay tai khoan"),
	ACCOUNT_PASSWORD_INVALID(404,"Mat khau phai tu 8 ky tu tro len"),
	LOGIN_FAILED(405,"Dang nhap that bai"),
	;
	
	private int code;
	private String message;
	
	 
	
	private ErrorCode(int code, String masage) {
		this.code = code;
		this.message = masage;
	}
	public int getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
	
	
}
