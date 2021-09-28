package me.sseob.demowebmvc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest
public class HandlerMethodControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Test
	public void handlerMethodHelloTest() throws Exception{
		mockMvc.perform(
						get("/handlerMethodEvents/1;name=sseob")
				)
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("id").value(1))
				.andExpect(jsonPath("name").value("sseob"))
		;
	}
	
	@Test
	public void eventsRequestParam() throws Exception{
		mockMvc.perform(
						post("/eventsRequestParam")
								.param("name", "sseob")
								.param("limit", "20")
				)
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("name").value("sseob"))
		;
	}

	/*
		thyme leaf를 사용하면 View name과 model 객체도 테스트 가능하다 ! 이건 몰랐네..
	 */
	@Test
	public void eventsForm() throws Exception{
		mockMvc.perform(
						get("/handlerMethodEvents/form")
				)
				.andDo(print())
				.andExpect(view().name("events/form"))
				.andExpect(model().attributeExists("event"))
		;
	}
	
	@Test
	public void eventsModelAttribute() throws Exception{
		mockMvc.perform(
						post("/eventsModelAttribute?id=3")
								.param("name", "sseob")
								.param("limit", "-10")
				)
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("name").value("sseob"))
		;
	}
}