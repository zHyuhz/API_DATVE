package com.DoAnChuyenNganh.UngDungDatVeXemPhim.Entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id") 
	private int user_id;

	@Column(name = "fullName", length = 255, nullable = false,columnDefinition = "nvarchar(MAX)")
	private String fullName;

	@Column(name = "birthday")
	private LocalDate birthday;

	@Column(name = "gender")
	private int gender;
	
	@Column(name = "city", length = 50, nullable = false)
	private String city;

	@Column(name = "phone", length = 20, nullable = false)
	private String phoneNumber;
	@JsonBackReference
	@OneToOne
	@JoinColumn(name = "account_id", unique = true, nullable = true)
	private Accounts accounts;
	@JsonManagedReference
	@OneToMany(mappedBy = "user")
	private List<Booking> bookings;

}
