package Response;

import java.time.LocalDate;
import java.util.List;

import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Entity.Schedule;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Entity.Seats;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Entity.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingReponse {
	
	private int booking_id;

	private Double price;

	private LocalDate bookingDate;
	@JsonManagedReference
	private List<SeatResponse> seatsBooking;
	@JsonBackReference
	private UserResponse user;
	
	private ScheduleResponse schedule;
}
