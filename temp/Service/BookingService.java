package Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Entity.Booking;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Entity.Schedule;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Entity.User;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Mapper.BookingMapper;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Repo.BookingRepo;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Repo.ScheduleRepo;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Repo.UserRepo;

import Response.BookingReponse;
import resquest.BookingRequest;

@Service
public class BookingService {

	@Autowired
	BookingRepo bookingRepo;
	@Autowired
	BookingMapper bookingMapper;
	@Autowired
	UserRepo userRepo;
	@Autowired
	ScheduleRepo scheduleRepo;

//	public List<Booking> getAllBookings() {
//		return bookingRepo.findAll();
//	}
	// getAllBookings
	public List<BookingReponse> getAllBookings() {
		List<Booking> list = bookingRepo.findAll();
		return list.stream().map(bookingMapper::toBookingReponse).toList();
	}

//addBooking
	public BookingReponse addBooking(BookingRequest request) {

		User user = userRepo.findById(request.getUser_id()).orElseThrow(() -> new RuntimeException("USER NOT FOUND"));

		Schedule schedule = scheduleRepo.findById(request.getSchedule_id())
				.orElseThrow(() -> new RuntimeException("Schedule NOT FOUND"));

		Booking booking = Booking.builder().price(request.getPrice()).bookingDate(request.getBookingDate()).user(user)
				.schedule(schedule).build();
		return bookingMapper.toBookingReponse(bookingRepo.save(booking));
	}
	
	public String deleteBooking(int id) {
		bookingRepo.deleteById(id);
		return "DA XOA THANH CONG: " + String.valueOf(id);
	}
}
