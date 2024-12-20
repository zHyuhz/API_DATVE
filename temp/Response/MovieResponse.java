package Response;

import java.time.LocalDate;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieResponse {

	private int movieId;

	private String movieName;

	private String movie_description;

	private String movie_genres;

	private LocalDate movie_release;

	private int movie_length;
	
	private String movie_poster;
	
}
