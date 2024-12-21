package Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Entity.Room;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Entity.Seats;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Mapper.SeatMapper;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Repo.RoomRepo;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Repo.SeatRepo;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.dto.response.SeatResponse;

import resquest.SeatRequest;

@Service
public class SeatService {

	@Autowired
	SeatRepo seatRepo;
	@Autowired
	RoomRepo roomRepo;
	@Autowired
	SeatMapper seatMapper;

	public List<SeatResponse> getAllSeats() {
		List<Seats> list = seatRepo.findAll();
		return list.stream().map(seatMapper::toSeatResponse).toList();
	}

	// addSeat
	public SeatResponse addSeat(SeatRequest request) {
		Room room = roomRepo.findById(request.getRoom_id()).orElseThrow(() -> new RuntimeException("Room not found"));

		Seats seats = Seats.builder().seatType(request.getSeatType()).seatRow(request.getSeatRow())
				.seatNumber(request.getSeatNumber()).isAvailable(false).room(room).build();
		return seatMapper.toSeatResponse(seatRepo.save(seats));
	}
//updateSeat
	public SeatResponse updateSeat(int id, SeatRequest request) {
				Seats seats = seatRepo.findById(id).orElseThrow(() -> new RuntimeException("Seats not found "));
			if(request.getSeatType() != null ) {
				seats.setSeatType(request.getSeatType());
			}
			if(request.getRoom_id() > 0 ) {
				Room room = roomRepo.findById(request.getRoom_id()).orElseThrow(() -> new RuntimeException("Room not found"));
				seats.setRoom(room);
			}
			if(request.getSeatNumber()>0) {
				seats.setSeatNumber(request.getSeatNumber());
			}
			if(request.getSeatRow() != null) {
				seats.setSeatRow(request.getSeatRow());
			}
			return seatMapper.toSeatResponse(seatRepo.save(seats));
	}
	public String deleteSeat(int id) {
		   if (!seatRepo.existsById(id)) {
		        throw new RuntimeException("Seat with ID " + id + " does not exist.");
		    }
		    seatRepo.deleteById(id);
		    return "Da xoa ghe co ID: " + id;
	}
	public List<SeatResponse> getSeatsByMovie(int id){
		List<Seats> list = seatRepo.findByRoomRoomId(id);
		return list.stream().map(seatMapper::toSeatResponse).toList();
	}
	
//	public List<SeatResponse> getSeatByroom(int id){
//		List<Seats> list = seatRepo.findByRoomRoomId(id);
//		return list.stream().map(seatMapper::toSeatResponse).toList();
//	}
	
	public SeatResponse getSeat(char seatRow, int seatNumber, int roomId) {
		return seatMapper.toSeatResponse(seatRepo.findBySeatRowAndSeatNumberAndRoomRoomId(seatRow, seatNumber, roomId));
	}
	public SeatResponse updateAvailable(char seatRow, int seatNumber, int roomId) {
		Seats seats = seatRepo.findBySeatRowAndSeatNumberAndRoomRoomId(seatRow, seatNumber, roomId);
		seats.setIsAvailable(false);
		
		return seatMapper.toSeatResponse(seatRepo.save(seats));
	}
	
	
}
