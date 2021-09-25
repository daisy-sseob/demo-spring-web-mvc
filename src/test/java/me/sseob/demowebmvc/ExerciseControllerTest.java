package me.sseob.demowebmvc;

import org.junit.Test;
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
public class ExerciseControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Test
	public void eventsTest() throws Exception{
		mockMvc.perform(get("/events/1"))
				.andExpect(status().isOk())
				.andExpect(content().string("events " + 1));
		mockMvc.perform(
						post("/events/2")
								.accept(MediaType.APPLICATION_JSON)
								.contentType(MediaType.APPLICATION_JSON)
				)
				.andExpect(status().isOk())
				.andExpect(content().string("events " + 2));
		mockMvc.perform(
						put("/events/3")
								.accept(MediaType.APPLICATION_JSON)
								.contentType(MediaType.APPLICATION_JSON)
				)
				.andExpect(status().isOk())
				.andExpect(content().string("events " + 3));
		mockMvc.perform(delete("/events/4"))
				.andExpect(status().isOk())
				.andExpect(content().string("events " + 4))
		;
	}
}