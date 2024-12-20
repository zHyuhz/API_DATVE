package Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Entity.Accounts;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Enums.Role;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Exception.AppException;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Exception.ErrorCode;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Mapper.AccountMapper;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Repo.AccountRepo;

import Response.AccountResponse;
import jakarta.transaction.Transactional;
import resquest.RegisterAccountRequest;
import resquest.UpdateAccountRequest;

@Service
public class AccountService {
	@Autowired
	private AccountRepo accountRepo;
	@Autowired
	private AccountMapper accountMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;

	// lấy danh sách tài khoản
	public List<AccountResponse> getAllAccount() {
		List<Accounts> list =  accountRepo.findAll();
		return list.stream().map(accountMapper::toAccountResponse).toList();
	}

	// Kiểm tra username đã tồn tại hay chưa
	public boolean existsByuserName(String username) {
		return accountRepo.existsByuserName(username);
	}

	// Kiểm tra email đã tồn tại hay chưa
	public boolean existsByemail(String email) {
		return accountRepo.existsByemail(email);
	}

	// Tìm tài khoản
	public AccountResponse getAccount(String userName) {
		return accountMapper.toAccountResponse(accountRepo.findByuserName(userName).orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_NOT_FOUND)));
	}
	

	// Cập nhật tài khoản
	public AccountResponse updateAccount(String userName, UpdateAccountRequest request) {
		Accounts account = accountRepo.findByuserName(userName).orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_NOT_FOUND));
		
		request.setPassword(passwordEncoder.encode(request.getPassword()));
		accountMapper.updateAccount(account, request);

		return accountMapper.toAccountResponse(accountRepo.save(account)) ;
	}

	// Xóa tài khoản
	@Transactional
	public void deleteAccount(String userName) {
		accountRepo.deleteByuserName(userName);
	}

	// Tạo tài khoản
	public AccountResponse createAccount(RegisterAccountRequest request) {

		if (existsByuserName(request.getUserName())) {
			throw new AppException(ErrorCode.ACCOUNT_USERNAME_EXISTED);
		}
		if (existsByemail(request.getEmail())) {
			throw new AppException(ErrorCode.ACCOUNT_EMAIL_EXISTED);
		}

		Accounts account = accountMapper.toAccounts(request);
		account.setPassword(passwordEncoder.encode(request.getPassword()));
		account.setCreatedAt(LocalDateTime.now());
		account.setStatus(1);
		account.setAccountRole(String.valueOf(Role.USER.name()));
		return accountMapper.toAccountResponse(accountRepo.save(account)) ;
	}
}
