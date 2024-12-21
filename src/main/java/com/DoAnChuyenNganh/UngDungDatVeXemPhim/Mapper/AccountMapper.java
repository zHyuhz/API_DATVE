package com.DoAnChuyenNganh.UngDungDatVeXemPhim.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Entity.Accounts;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.dto.request.RegisterAccountRequest;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.dto.request.UpdateAccountRequest;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.dto.response.AccountResponse;

@Mapper(componentModel = "spring")
public interface AccountMapper {
	
	Accounts toAccounts(RegisterAccountRequest request);
	AccountResponse toAccountResponse(Accounts accounts);
	public void updateAccount(@MappingTarget Accounts account, UpdateAccountRequest request);

}
