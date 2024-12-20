package Repo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Entity.Booking;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Entity.Schedule;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Entity.User;

public interface BookingRepo extends JpaRepository<Booking, Integer> {
	   List<Booking> findByUser(User user);
	    
	    // Tìm Booking theo Schedule
	    List<Booking> findBySchedule(Schedule schedule);
	    
	    // Tìm Booking theo ID
	    Optional<Booking> findById(int id);
	    
	    // Tìm tất cả booking theo ngày
	    List<Booking> findByBookingDate(LocalDate bookingDate);

}
