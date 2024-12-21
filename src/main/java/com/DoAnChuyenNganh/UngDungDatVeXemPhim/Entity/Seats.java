package com.DoAnChuyenNganh.UngDungDatVeXemPhim.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToOne;
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
@Table(name = "seats")
public class Seats {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int seat_id;

	@Column(name = "seat_type")
	private String seatType;

	@Column(name = "seat_row")
	private Character seatRow;

	@Column(name = "seat_number")
	private int seatNumber;

	@Column(name = "isAvailable", nullable = false, columnDefinition = "BIT DEFAULT 1")
	private Boolean isAvailable;
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "room_id", nullable = true)
	private Room room;
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "booking_id", nullable = true)
	private Booking booking;

}
