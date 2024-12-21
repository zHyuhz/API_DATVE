package com.DoAnChuyenNganh.UngDungDatVeXemPhim.Entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
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
@Table(name = "movies")
public class Movies {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int movieId;
	@Column(name = "movie_name", length = 50)
	private String movieName;

	@Lob
    @Column(name = "movie_description", columnDefinition = "nvarchar(MAX)")
	private String movie_description;

	@Column(name = "movie_genres")
	private String movie_genres;

	@Column(name = "movie_release")
	private LocalDate movie_release;

	@Column(name = "movie_length")
	private int movie_length;

	@Column(name = "movie_poster")
	private String movie_poster;
	
	@JsonManagedReference
	 @OneToMany(mappedBy = "movies", cascade = CascadeType.ALL, orphanRemoval = true)
	List<Schedule> listSchedules;

}
