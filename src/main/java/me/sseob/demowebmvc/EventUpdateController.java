package me.sseob.demowebmvc;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE
)
public class EventUpdateController {
	
	@PostMapping(value = "/event/{id}")
	public String createEvents(@PathVariable int id) {
		return "event " + id;
	}
	
	@PutMapping(value = "/event/{id}")
	public String updateEvents(@PathVariable int id) {
		return "event " + id;
	}
}
