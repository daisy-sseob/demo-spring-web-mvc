package me.sseob.demowebmvc;

import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;

@Controller
public class FileController {
	
	private final ResourceLoader resourceLoader;
	private final Tika tika;

	@Autowired
	public FileController(ResourceLoader resourceLoader, Tika tika) {
		this.resourceLoader = resourceLoader;
		this.tika = tika;
	}

	@GetMapping("/file")
	public String filUploadForm() {
		return "files/index";
	}

	@PostMapping("/file")
	public String fileUpload(@RequestParam MultipartFile file,
	                         RedirectAttributes redirectAttributes) {

		// 물리적으로 저장했다는 가정하에 아래 코두 진행
		String msg = file.getOriginalFilename() + " is uploaded";

		redirectAttributes.addFlashAttribute("msg", msg);

		return "redirect:/file";
	}

	@GetMapping("/file/{filename}")
	public ResponseEntity<Resource> fileDownload(@PathVariable String filename) throws IOException {
		Resource resource = resourceLoader.getResource("classpath:" + filename);
		File file = resource.getFile();
		
		// tika libaray를 이용한 mediatype 알아내기
		String mediaType = tika.detect(file);
		
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.header(HttpHeaders.CONTENT_TYPE, mediaType)
				.header(HttpHeaders.CONTENT_LENGTH, file.length() + "")
				.body(resource)
				;
	}
}
