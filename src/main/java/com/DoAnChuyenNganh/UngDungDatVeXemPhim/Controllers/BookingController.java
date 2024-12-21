package com.DoAnChuyenNganh.UngDungDatVeXemPhim.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Services.BookingService;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.dto.request.ApiResponse;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.dto.request.BookingRequest;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.dto.response.BookingReponse;

@RestController
@RequestMapping("/bookings")
public class BookingController {
	
	@Autowired
	BookingService bookingService;
	
//	 @GetMapping
//	    public List<Booking> getAllBookings() {
//	        List<Booking> bookings = bookingService.getAllBookings();
//	        return bookings;
//	    }

	@GetMapping
	public ApiResponse<List<BookingReponse>> getAllBooking(){
		return ApiResponse.<List<BookingReponse>>builder()
				.result(bookingService.getAllBookings())
				.build();
	}
	@PostMapping
	public ApiResponse<BookingReponse> addBooking(@RequestBody BookingRequest request){
		return  ApiResponse.<BookingReponse>builder()
				.result(bookingService.addBooking(request))
				.build();
	}
	@DeleteMapping("/{id}")
	public  ApiResponse<String> deleteBooking(@PathVariable int id){
		return ApiResponse.<String>builder()
				.result(bookingService.deleteBooking(id))
				.build();
	}
}
