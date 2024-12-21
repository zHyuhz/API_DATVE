package com.DoAnChuyenNganh.UngDungDatVeXemPhim.Controllers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("uploads/img")
public class UploadsImageController {
	@GetMapping("/{photo}")
	 public ResponseEntity<ByteArrayResource>  getImage(@PathVariable("photo") String photo) {
		 if (!photo.equals("") || photo != null) {
	            try {
	                // Đường dẫn tới tệp
	                Path filename = Paths.get("uploads", photo);

	                // Đọc toàn bộ dữ liệu từ tệp
	                byte[] buffer = Files.readAllBytes(filename);

	                // Chuyển đổi dữ liệu thành ByteArrayResource để trả về
	                ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);
	                
	                // Trả về ResponseEntity chứa ảnh
	                return ResponseEntity.ok()
	                        .contentLength(buffer.length)
	                        .contentType(MediaType.parseMediaType("image/png"))  // Đặt MediaType tương ứng (image/png)
	                        .body(byteArrayResource);

	            } catch (Exception e) {
	                // Xử lý ngoại lệ khi không tìm thấy tệp hoặc lỗi khác
	                e.printStackTrace();
	            }
	        }

	        // Trả về lỗi nếu không nhận được ảnh
	        return ResponseEntity.badRequest().build();
	}
}


