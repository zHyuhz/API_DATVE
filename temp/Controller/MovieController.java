package Controller;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Services.MovieService;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.dto.response.MovieResponse;

import resquest.ApiResponse;
import resquest.MovieRequest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/movies")
public class MovieController {
	@Autowired
	private MovieService movieService;
	
	
//Lấy All phim
	@GetMapping
	public ApiResponse<List<MovieResponse>> getAllMovie() {
		return ApiResponse.<List<MovieResponse>>builder().result(movieService.getAllMovies()).build();
	}
	
	
//Thêm phim
	@PostMapping
	public ApiResponse<MovieResponse> addMovies(@RequestParam("movieName") String movieName,
																									@RequestParam("movieDes") String movieDes,
																									@RequestParam("movieGenres") String movieGenres,
																									@RequestParam("movieRelease") String movieRelease,
																									@RequestParam("movieLength") int movieLength,
																									@RequestParam("moviePoster") MultipartFile moviePoster) {
		String trimmedMovieRelease = movieRelease.trim();
		LocalDate parsedReleaseDate = LocalDate.parse(trimmedMovieRelease); 
		
		MovieRequest request = new MovieRequest();
		request.setMovie_name(movieName);
		request.setMovie_description(movieDes);
		request.setMovie_genres(movieGenres);
		request.setMovie_release(parsedReleaseDate);
		request.setMovie_length(movieLength);
		return ApiResponse.<MovieResponse>builder()
				.result(movieService.addMovie(request, moviePoster))
				.build();
	}
	
	
	//Cập nhật phim
	@PutMapping("/{movieId}")
	public ApiResponse<MovieResponse> updateMovie(
	        @PathVariable("movieId") int movieId,
	        @RequestParam("movieName") String movieName,
	        @RequestParam("movieDes") String movieDes,
	        @RequestParam("movieGenres") String movieGenres,
	        @RequestParam("movieRelease") String movieRelease,
	        @RequestParam("movieLength") int movieLength,
	        @RequestParam(value = "moviePoster") MultipartFile moviePoster) {

	    // Chuyển đổi dữ liệu từ request thành MovieRequest
	    MovieRequest request = new MovieRequest();
	    request.setMovie_name(movieName);
	    request.setMovie_description(movieDes);
	    request.setMovie_genres(movieGenres);
	    request.setMovie_release(LocalDate.parse(movieRelease));
	    request.setMovie_length(movieLength);

	    // Gọi service để cập nhật thông tin phim
	    MovieResponse updatedMovie = movieService.updateMovie(movieId, request, moviePoster);

	    return ApiResponse.<MovieResponse>builder()
	            .result(updatedMovie)
	            .build();
	}
	//Tìm phim theo tên
	  @GetMapping("/search")
	    public ApiResponse<List<MovieResponse>> searchMoviesByName(@RequestParam("name") String name) {
	        List<MovieResponse> movies = movieService.findMoviesByName(name);
	        return ApiResponse.<List<MovieResponse>>builder()
	                .result(movies)
	                .build();
	    }

	  @DeleteMapping("/{movieId}")
		public ApiResponse<String> deleteMovie(@PathVariable int movieId) {
			movieService.deleteMovie(movieId);
			return ApiResponse.<String>builder()
					.result("Xoa " + movieId + " thanh cong!!")
					.build()  ;
		}
	  @GetMapping("/{movieId}")
			public ApiResponse<MovieResponse> findMovieByID(@PathVariable int movieId) {
				return ApiResponse.<MovieResponse>builder()
						.result(	movieService.findMovieByID(movieId))
						.build()  ;
			}
}
