package com.DoAnChuyenNganh.UngDungDatVeXemPhim.dto.request;

import java.time.LocalDate;
import java.time.LocalTime;




import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScheduleRequest {
	
	private int movie_id;

	private int room_id;

	private LocalDate scheduleDate;

	private LocalTime scheduleStart;

	private LocalTime scheduleEnd;

	private double price;

}
