package com.DoAnChuyenNganh.UngDungDatVeXemPhim.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Entity.Movies;

public interface MovieRepo extends JpaRepository<Movies, Integer> {
	public List<Movies> findBymovieNameContainingIgnoreCase(String movie_name);
}
