package me.sseob.demowebmvc;

import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/api/events")
public class EventApi {

	/*
		HttpEntity로 header정보까지 받기
	@PostMapping
	public Event createEvent(@Valid HttpEntity<Event> request) {
		MediaType contentType = request.getHeaders().getContentType();
		System.out.println(contentType);
		return request.getBody();
	}
	 */
	
	@PostMapping
	public ResponseEntity<Event> createEvent(@Valid @RequestBody Event event, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(event);
	}
}
