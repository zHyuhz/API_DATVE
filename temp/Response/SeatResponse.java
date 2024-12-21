package Response;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeatResponse {
private int seat_id;
	
	private String seatType;
	
	private Character seatRow;
	
	private int seatNumber;

	private Boolean isAvailable ; // Giá trị mặc định ở mức ứng dụng
	
	//private RoomResponse room;
	
}
