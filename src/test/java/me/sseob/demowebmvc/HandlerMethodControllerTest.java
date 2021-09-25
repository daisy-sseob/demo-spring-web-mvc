package me.sseob.demowebmvc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class HandlerMethodControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Test
	public void handlerMethodHelloTest() throws Exception{
		mockMvc.perform(
					get("/handlerMethodEvents/1")
				)
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("id").value(1))
		;
	}
}