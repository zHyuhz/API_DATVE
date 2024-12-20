package Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Entity.User;

public interface UserRepo extends JpaRepository<User,Integer> {

}
