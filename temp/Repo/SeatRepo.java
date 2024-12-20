package Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Entity.Seats;

public interface SeatRepo extends JpaRepository<Seats, Integer> {
	public List<Seats> findByRoomRoomId(int id);
	public Seats findBySeatRowAndSeatNumberAndRoomRoomId(char seatRow, int seatNumber, int roomId );
}
