package Controller;

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

import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Services.SeatService;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.dto.response.SeatResponse;

import resquest.ApiResponse;
import resquest.SeatRequest;

@RestController
@RequestMapping("/seats")
public class SeatController {

	@Autowired
	SeatService seatService;

	@GetMapping
	public ApiResponse<List<SeatResponse>> getAllSeats() {
		return ApiResponse.<List<SeatResponse>>builder().result(seatService.getAllSeats()).build();
	}

	@PostMapping
	public ApiResponse<SeatResponse> addSeat(@RequestBody SeatRequest request) {
		return ApiResponse.<SeatResponse>builder().result(seatService.addSeat(request)).build();
	}

	@DeleteMapping("/{id}")
	public ApiResponse<String> deleteSeat(@PathVariable int id) {
		return ApiResponse.<String>builder().result(seatService.deleteSeat(id)).build();
	}

	@PutMapping("/{id}")
	public ApiResponse<SeatResponse> updateSeat(@PathVariable int id, @RequestBody SeatRequest request) {
		return ApiResponse.<SeatResponse>builder().result(seatService.updateSeat(id, request)).build();
	}

	@GetMapping("/{id}")
	public ApiResponse<List<SeatResponse>> getAllSeats(@PathVariable int id) {
		return ApiResponse.<List<SeatResponse>>builder().result(seatService.getSeatsByMovie(id)).build();
	}
//	@GetMapping("/getbyroom/{id}")
//	public ApiResponse<List<SeatResponse>> getSeatByroom(@PathVariable int id) {
//		return ApiResponse.<List<SeatResponse>>builder().result(seatService.getSeatsByMovie(id)).build();
//	}

	@GetMapping("/search")
	public ApiResponse<SeatResponse> getSeat(@RequestParam char seatRow,  @RequestParam int seatNumber,  @RequestParam  int roomId ){
		return ApiResponse.<SeatResponse>builder().result(seatService.getSeat(seatRow, seatNumber, roomId)).build();
	
}
	@PutMapping("/choose")
	public ApiResponse<SeatResponse> updateAvailable(@RequestParam char seatRow,  @RequestParam int seatNumber,  @RequestParam  int roomId ){
		
		System.out.println("Received seatRow: " + seatRow);
	    System.out.println("Received seatNumber: " + seatNumber);
	    System.out.println("Received roomId: " + roomId);
		return ApiResponse.<SeatResponse>builder().result(seatService.updateAvailable(seatRow, seatNumber, roomId)).build();
	}
}