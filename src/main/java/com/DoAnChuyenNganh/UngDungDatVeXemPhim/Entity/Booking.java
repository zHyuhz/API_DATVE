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

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "booking")

public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int booking_id;

	@Column(name = "price")
	private Double price;

	@Column(name = "booking_date")
	private LocalDate bookingDate;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "booking")
	private List<Seats> seatsBooking;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = true)
	private User user;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "schedule_id", nullable = true)
	private Schedule schedule;

}
