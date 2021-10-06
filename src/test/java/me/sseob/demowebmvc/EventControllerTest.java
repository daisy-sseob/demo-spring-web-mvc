package me.sseob.demowebmvc;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class EventControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Test
	public void eventsTest() throws Exception{
		mockMvc.perform(get("/events"))
				.andExpect(status().isOk())
				.andExpect(content().string("events"));
		
		mockMvc.perform(get("/event/1"))
				.andExpect(status().isOk())
				.andExpect(content().string("event " + 1));
		
		mockMvc.perform(
						post("/event/2")
								.accept(MediaType.APPLICATION_JSON)
								.contentType(MediaType.APPLICATION_JSON)
				)
				.andExpect(status().isOk())
				.andExpect(content().string("event " + 2));
		
		mockMvc.perform(
						put("/event/3")
								.accept(MediaType.APPLICATION_JSON)
								.contentType(MediaType.APPLICATION_JSON)
				)
				.andExpect(status().isOk())
				.andExpect(content().string("event " + 3));
		
		mockMvc.perform(delete("/event/4"))
				.andExpect(status().isOk())
				.andExpect(content().string("event " + 4))
		;
	}
}