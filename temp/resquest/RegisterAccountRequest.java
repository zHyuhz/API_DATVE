package resquest;

import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import lombok.experimental.FieldDefaults;

@Data // tạo các hàm getter/setter...
@NoArgsConstructor // tạo construstor không đối số
@AllArgsConstructor
@Builder // tạo Obj nhanh hơn Ex: Object.builder().Attribute1...buid()
@FieldDefaults(level = AccessLevel.PRIVATE) // Đặt mặc định các thuộc tính không khai báo cụ thể là private
public class RegisterAccountRequest {
	String userName;

	@Size(min = 8, message = "ACCOUNT_PASSWORD_INVALID")
	String password;

	String email;

}
