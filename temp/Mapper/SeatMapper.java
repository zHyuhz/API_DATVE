package Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Entity.Room;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Entity.Seats;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Repo.RoomRepo;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.dto.response.SeatResponse;

import resquest.SeatRequest;

@Mapper(componentModel = "spring")
public abstract class SeatMapper {

	@Autowired
	RoomRepo roomRepo;
    @Mapping(target = "room", expression = "java(getRoomById(request.getRoom_id()))")
    public abstract Seats  toSeats(SeatRequest request) ;
	
    public abstract SeatResponse toSeatResponse(Seats seats);
    
    protected Room getRoomById(int roomId) {
        return roomRepo.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + roomId));
    }

}
