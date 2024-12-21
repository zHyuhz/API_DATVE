package Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Entity.Movies;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Exception.AppException;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Exception.ErrorCode;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Mapper.MoviesMapper;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Repo.MovieRepo;

import Response.MovieResponse;
import resquest.MovieRequest;

@Service
public class MovieService {
	@Autowired
	private MovieRepo movieRepo;
	@Autowired
	private MoviesMapper moviesMapper;

	public List<MovieResponse> getAllMovies() {
		 // Lấy danh sách Movies từ database
	    List<Movies> moviesList = movieRepo.findAll();

	    // Chuyển đổi từng Movies thành MovieResponse bằng MovieMapper
	    return moviesList.stream()
	                     .map(moviesMapper::toMovieResponse)
	                     .toList(); // Hoặc sử dụng Collectors.toList()
	}

//thêm phim
	public MovieResponse addMovie(MovieRequest request, MultipartFile moviePoster) {

		Movies movies = new Movies();
		movies.setMovieName(request.getMovie_name());
		movies.setMovie_description(request.getMovie_description());
		movies.setMovie_genres(request.getMovie_genres());
		movies.setMovie_release(request.getMovie_release());
		movies.setMovie_length(request.getMovie_length());

		if (moviePoster.isEmpty()) {
			throw new IllegalArgumentException("Movie poster is required.");
		}
		Path path = Paths.get("uploads/");
		try {
			if (!Files.exists(path)) {
				Files.createDirectories(path); // Tạo thư mục nếu chưa tồn tại
			}
			InputStream inputStream = moviePoster.getInputStream();
			Files.copy(inputStream, path.resolve(moviePoster.getOriginalFilename()),
					StandardCopyOption.REPLACE_EXISTING);
			movies.setMovie_poster("/uploads/img/" + moviePoster.getOriginalFilename().toLowerCase());

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return moviesMapper.toMovieResponse(movieRepo.save(movies));
	}

//sửa phim
	public MovieResponse updateMovie(int movieId, MovieRequest request, MultipartFile moviePoster) {
		// Tìm phim theo ID
		Movies movies = movieRepo.findById(movieId)
				.orElseThrow(() -> new IllegalArgumentException("Movie with ID " + movieId + " not found."));

		// Cập nhật các thông tin trong request nếu không null
		if (request.getMovie_name() != null) {
			movies.setMovieName(request.getMovie_name());
		}
		if (request.getMovie_description() != null) {
			movies.setMovie_description(request.getMovie_description());
		}
		if (request.getMovie_genres() != null) {
			movies.setMovie_genres(request.getMovie_genres());
		}
		if (request.getMovie_release() != null) {
			movies.setMovie_release(request.getMovie_release());
		}
		if (request.getMovie_length() > 0) {
			movies.setMovie_length(request.getMovie_length());
		}

		// Nếu có poster mới, thay thế poster cũ
		if (moviePoster != null && !moviePoster.isEmpty()) {
			Path uploadPath = Paths.get("uploads/");
			try {
				if (!Files.exists(uploadPath)) {
					Files.createDirectories(uploadPath);
				}

				// Lưu poster mới
				String posterFileName = moviePoster.getOriginalFilename().toLowerCase();
				Path targetPath = uploadPath.resolve(posterFileName);
				InputStream inputStream = moviePoster.getInputStream();
				Files.copy(inputStream, targetPath, StandardCopyOption.REPLACE_EXISTING);

				// Cập nhật đường dẫn poster
				movies.setMovie_poster("/uploads/img/" + posterFileName);
			} catch (IOException e) {
				throw new RuntimeException("Failed to store new movie poster: " + e.getMessage());
			}
		}

		// Lưu thông tin phim đã cập nhật
		Movies updatedMovie = movieRepo.save(movies);
		return moviesMapper.toMovieResponse(updatedMovie);
	}

		//tìm phim theo tên 
	public List<MovieResponse> findMoviesByName(String movieName) {
        List<Movies> movies = movieRepo.findBymovieNameContainingIgnoreCase(movieName);
        return movies.stream()
                .map(moviesMapper::toMovieResponse)
                .collect(Collectors.toList());
    }
	//Xóa phim bằng ID
	public void deleteMovie(int movieId) {
		movieRepo.deleteById(movieId);
	}
	//Tìm bằng id
	public MovieResponse  findMovieByID(int movieId) {
		return moviesMapper.toMovieResponse(movieRepo.findById(movieId).orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_NOT_FOUND)))	;
	}
}
