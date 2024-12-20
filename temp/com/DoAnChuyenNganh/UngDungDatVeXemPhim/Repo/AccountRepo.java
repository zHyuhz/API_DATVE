package com.DoAnChuyenNganh.UngDungDatVeXemPhim.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Entity.Accounts;

public interface AccountRepo extends JpaRepository<Accounts, Integer> {
	
	public boolean existsByuserName(String userName);
	public boolean existsByemail(String email);
	public Optional<Accounts> findByuserName(String userName);
	public void deleteByuserName(String userName);

}
