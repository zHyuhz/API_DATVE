package com.DoAnChuyenNganh.UngDungDatVeXemPhim.Entity;

import java.time.LocalDate;

import java.time.LocalTime;
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
@Table(name = "schedule")
public class Schedule {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int schedule_id;
		
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "movie_id", nullable = false)
	private Movies movies;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "room_id", nullable = false)
	private Room room;

	@Column(name = "schedule_date", nullable = false)
	private LocalDate scheduleDate;
	@Column(name = "schedule_start", nullable = false)
	private LocalTime scheduleStart;
	@Column(name = "schedule_end", nullable = false)
	private LocalTime scheduleEnd;
	@Column(name = "price", nullable = false)
	private double price;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "schedule")
	private List<Booking> bookings; 
	

}
