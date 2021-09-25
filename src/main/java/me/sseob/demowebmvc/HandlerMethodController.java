package me.sseob.demowebmvc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HandlerMethodController {

	@GetMapping("/handlerMethodEvents/{id}")
	public Event handlerMethodHello(@PathVariable Integer id) {
		Event event = new Event();
		event.setId(id);
		return event;
	}
}
