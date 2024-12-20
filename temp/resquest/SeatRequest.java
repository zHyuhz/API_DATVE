package resquest;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeatRequest {
	
	private String seatType;
	
	private Character seatRow;
	
	private int seatNumber;
	
	private int  room_id;
	
}
