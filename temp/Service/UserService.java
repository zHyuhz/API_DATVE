package Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Entity.Accounts;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Entity.User;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Mapper.UserMapper;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Repo.AccountRepo;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Repo.UserRepo;

import Response.UserResponse;
import resquest.UserRequest;

@Service
public class UserService {

	@Autowired
	UserRepo userRepo;
	@Autowired
	UserMapper userMapper;
	@Autowired
	AccountRepo accountRepo;

	public List<UserResponse> getAllUser() {
		List<User> list = userRepo.findAll();
		return list.stream().map(userMapper::toUserResponse).toList();
	}

	public String deleteUser(int id) {
		userRepo.deleteById(id);
		return "DA XOA USER: " + String.valueOf(id);
	}

	public UserResponse updateUser(int id, UserRequest request) {
		 User existingUser = userRepo.findById(id).orElseThrow(() -> new RuntimeException());

		    // Cập nhật các trường từ request nếu không null, giữ nguyên trường cũ nếu không có trong request
		    if (request.getFullName() != null) {
		        existingUser.setFullName(request.getFullName());
		    }
		    if (request.getBirthday() != null) {
		        existingUser.setBirthday(request.getBirthday());
		    }
		    if (request.getGender() >= 0 &&request.getGender()<=1) { // Giả sử gender là số, 0 là giá trị không hợp lệ
		        existingUser.setGender(request.getGender());
		    }
		    if (request.getCity() != null) {
		        existingUser.setCity(request.getCity());
		    }
		    if (request.getPhoneNumber() != null) {
		        existingUser.setPhoneNumber(request.getPhoneNumber());
		    }

		    // Lưu user đã cập nhật vào database
		    User updatedUser = userRepo.save(existingUser);

		    // Trả về response
		    return userMapper.toUserResponse(updatedUser);
	}
}
