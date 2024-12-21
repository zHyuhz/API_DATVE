package Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Entity.Movies;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Entity.Room;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Entity.Schedule;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Repo.MovieRepo;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Repo.RoomRepo;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.dto.response.ScheduleResponse;

import resquest.ScheduleRequest;

@Mapper(componentModel = "spring")
public abstract class ScheduleMapper {
	@Autowired
	MovieRepo movieRepo;
	
	@Autowired
	RoomRepo roomRepo;
	
    // Map ScheduleRequest -> Schedule
	@Mapping(target = "movies", expression = "java(getMovieById(request.getMovie_id()))")	
    @Mapping(target = "room", expression = "java(getRoomById(request.getRoom_id()))")
    public abstract Schedule toSchedule(ScheduleRequest request) ;
	
	// Map Schedule -> ScheduleResponse
    public abstract ScheduleResponse toScheduleResponse(Schedule schedule);
    
    
    protected Movies getMovieById(int movieId) {
        return movieRepo.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found with id: " + movieId));
    }

    // Helper method to fetch Room by ID
    protected Room getRoomById(int roomId) {
        return roomRepo.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + roomId));
    }
}
