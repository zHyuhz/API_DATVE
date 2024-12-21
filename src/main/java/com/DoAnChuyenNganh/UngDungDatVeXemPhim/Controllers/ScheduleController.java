package com.DoAnChuyenNganh.UngDungDatVeXemPhim.Controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Services.ScheduleService;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.dto.response.ScheduleResponse;

import jakarta.validation.Valid;
import resquest.ApiResponse;
import resquest.ScheduleRequest;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {
	@Autowired
	ScheduleService scheduleService;

	@GetMapping
	public ApiResponse<List<ScheduleResponse>> getAllSchedule() {
		return ApiResponse.<List<ScheduleResponse>>builder().result(scheduleService.getAllSchedule()).build();
	}

	@PostMapping
	public ApiResponse<ScheduleResponse> createSchedule(@RequestBody @Valid ScheduleRequest request) {
		return ApiResponse.<ScheduleResponse>builder().result(scheduleService.createSchedule(request)).build();
	}

	@DeleteMapping("/{id}")
	public ApiResponse<String> deleteSchedule(@PathVariable @Valid int id) {
		return ApiResponse.<String>builder().result(scheduleService.deleteSchedule(id)).build();
	}

	@PutMapping("/{id}")
	public ApiResponse<ScheduleResponse> updateSchedule(@PathVariable @Valid int id,
			@RequestBody ScheduleRequest request) {
		return ApiResponse.<ScheduleResponse>builder().result(scheduleService.updateSchedule(id, request)).build();
	}

	@GetMapping("/{id}")
	public ApiResponse<List<ScheduleResponse>> findScheduleByMovieId(@PathVariable int id) {
		return ApiResponse.<List<ScheduleResponse>>builder().result(scheduleService.findByMovieId(id)).build();
	}

	@GetMapping("/search")
	public ApiResponse<ScheduleResponse> findSchedules(@RequestParam LocalDate scheduleDate,
			@RequestParam LocalTime scheduleStart, @RequestParam int movieId) {
		ScheduleResponse schedules = scheduleService.getSchedules(scheduleDate, scheduleStart, movieId);
		return ApiResponse.<ScheduleResponse>builder().result(schedules).build();
	}
//	  @GetMapping("/search")
//	    public List<Schedule> findSchedules(
//	            @RequestParam LocalDate scheduleDate,
//	            @RequestParam LocalTime scheduleStart,
//	            @RequestParam int movieId) {
//	        List<Schedule> schedules = scheduleService.getSchedules(scheduleDate, scheduleStart, movieId);
//	        return   schedules;
//	    }
}
