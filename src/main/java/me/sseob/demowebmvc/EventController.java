package me.sseob.demowebmvc;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class EventController {
	
	@GetMapping(value = "/events")
	public String getEvents() {
		return "events";
	}
	
	@GetMapping(value = "/event/{id}")
	public String getAnEvents(@PathVariable int id) {
		return "event " + id;
	}
	
	@DeleteMapping(value = "/event/{id}")
	public String deleteEvents(@PathVariable int id) {
		return "event " + id;
	}
}
