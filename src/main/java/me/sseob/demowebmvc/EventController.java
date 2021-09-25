package me.sseob.demowebmvc;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class EventController {
	
	@GetMapping(value = "/events/{id}")
	public String getEvents(@PathVariable int id) {
		return "events " + id;
	}
	
	@DeleteMapping(value = "/events/{id}")
	public String deleteEvents(@PathVariable int id) {
		return "events " + id;
	}
}
