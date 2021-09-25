package me.sseob.demowebmvc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeadAndOptionsController {

	@GetMapping("/requestHead")
	public String requestHead() {
		return "requestHead";
	}
	
	@PostMapping("/requestHead")
	public String requestHeadPost() {
		return "requestHead";
	}
}
