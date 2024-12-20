package com.DoAnChuyenNganh.UngDungDatVeXemPhim.Mapper;

import org.mapstruct.Mapper;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Entity.Movies;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.dto.request.MovieRequest;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.dto.response.MovieResponse;

@Mapper(componentModel = "spring")
public interface MoviesMapper {
	
	Movies toMovies(MovieRequest request);
	MovieResponse toMovieResponse(Movies movies);
//	public void updateAccount(@MappingTarget Accounts account, UpdateAccountRequest request);
}
