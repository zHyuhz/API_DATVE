package com.DoAnChuyenNganh.UngDungDatVeXemPhim.dto.response;


import java.util.List;

import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Entity.Schedule;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Entity.Seats;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomResponse {
	
	private int roomId;
	
	private String roomName;

}
