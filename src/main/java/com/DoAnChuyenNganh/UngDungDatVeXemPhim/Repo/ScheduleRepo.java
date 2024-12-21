package com.DoAnChuyenNganh.UngDungDatVeXemPhim.Repo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Entity.Schedule;

public interface ScheduleRepo extends JpaRepository<Schedule, Integer> {
	
	public List<Schedule> findByMoviesMovieId(int id);

	public Schedule  findByScheduleDateAndScheduleStartAndMoviesMovieId(LocalDate scheduleDate, LocalTime scheduleStart, int movieId);

}
