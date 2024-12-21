package com.DoAnChuyenNganh.UngDungDatVeXemPhim.dto.response;

import java.time.LocalDate;
import java.time.LocalTime;

import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Entity.Room;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScheduleResponse {
	
	private int schedule_id;
	
	private MovieResponse movies;

	private RoomResponse room;

	private LocalDate scheduleDate;

	private LocalTime scheduleStart;

	private LocalTime scheduleEnd;

	private double price;

}
