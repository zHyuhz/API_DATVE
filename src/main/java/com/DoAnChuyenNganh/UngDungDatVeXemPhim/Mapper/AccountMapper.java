package com.DoAnChuyenNganh.UngDungDatVeXemPhim.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Entity.Accounts;

import Response.AccountResponse;
import resquest.RegisterAccountRequest;
import resquest.UpdateAccountRequest;

@Mapper(componentModel = "spring")
public interface AccountMapper {
	
	Accounts toAccounts(RegisterAccountRequest request);
	AccountResponse toAccountResponse(Accounts accounts);
	public void updateAccount(@MappingTarget Accounts account, UpdateAccountRequest request);

}
