package Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Entity.Movies;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Entity.Room;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Entity.Schedule;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Mapper.ScheduleMapper;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Repo.MovieRepo;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Repo.RoomRepo;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Repo.ScheduleRepo;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.dto.response.ScheduleResponse;

import resquest.ScheduleRequest;

@Service
public class ScheduleService {
	@Autowired
	ScheduleRepo scheduleRepo;
	@Autowired
	ScheduleMapper scheduleMapper;
	@Autowired
	MovieRepo movieRepo;
	@Autowired
	RoomRepo roomRepo;

	// getAllSchedule
	public List<ScheduleResponse> getAllSchedule() {
		List<Schedule> schedules = scheduleRepo.findAll();
		return schedules.stream().map(scheduleMapper::toScheduleResponse).toList();
	}

	// createSchedule
	public ScheduleResponse createSchedule(ScheduleRequest request) {
		Movies movie = movieRepo.findById(request.getMovie_id())
				.orElseThrow(() -> new RuntimeException("Movie not found"));

		Room room = roomRepo.findById(request.getRoom_id()).orElseThrow(() -> new RuntimeException("Room not found"));

		Schedule schedule = Schedule.builder().movies(movie).room(room).scheduleDate(request.getScheduleDate())
				.scheduleStart(request.getScheduleStart()).scheduleEnd(request.getScheduleEnd())
				.price(request.getPrice()).build();

		return scheduleMapper.toScheduleResponse(scheduleRepo.save(schedule));
	}

	// deleteSchedule
	public String deleteSchedule(int id) {
		scheduleRepo.deleteById(id);
		return "Xoa thanh cong :  " + String.valueOf(id);
	}

//updateSchedule
	public ScheduleResponse updateSchedule(int id, ScheduleRequest request) {

		Schedule schedule = scheduleRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Schedule  with ID " + id + " not found."));

		// Cập nhật các thông tin trong request nếu không null
		if (request.getMovie_id() > 0) {
			Movies movie = movieRepo.findById(request.getMovie_id())
					.orElseThrow(() -> new RuntimeException("Movie not found"));
			schedule.setMovies(movie);
		}
		if (request.getRoom_id() > 0) {
			Room room = roomRepo.findById(request.getRoom_id())
					.orElseThrow(() -> new RuntimeException("Room not found"));
			schedule.setRoom(room);
		}
		if (request.getPrice() >= 0) {
			schedule.setPrice(request.getPrice());
		}
		if (request.getScheduleDate() != null) {
			schedule.setScheduleDate(request.getScheduleDate());
		}
		if (request.getScheduleStart() != null) {
			schedule.setScheduleStart(request.getScheduleStart());
		}
		if (request.getScheduleEnd() != null) {
			schedule.setScheduleEnd(request.getScheduleEnd());
		}
		return scheduleMapper.toScheduleResponse(scheduleRepo.save(schedule));
	}

	public List<ScheduleResponse>  findByMovieId(int id) {
		List<Schedule> list   = scheduleRepo.findByMoviesMovieId(id);
		return list.stream().map(scheduleMapper::toScheduleResponse).toList();
	}
	public ScheduleResponse  getSchedules(LocalDate scheduleDate, LocalTime scheduleStart, int movieId) {
		Schedule response   = scheduleRepo.findByScheduleDateAndScheduleStartAndMoviesMovieId(scheduleDate, scheduleStart, movieId);
        return scheduleMapper.toScheduleResponse(response);
    }
//	public List<Schedule> getSchedules(LocalDate scheduleDate, LocalTime scheduleStart, int movieId) {
//	List<Schedule> list   = scheduleRepo.findByScheduleDateAndScheduleStartAndMoviesMovieId(scheduleDate, scheduleStart, movieId);
//    return list;
//}
}
