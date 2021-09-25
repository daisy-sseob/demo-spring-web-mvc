package me.sseob.demowebmvc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class SampleControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void helloTest() throws Exception {
		mockMvc.perform(get("/hello/123/11"))
				.andDo(print())
				.andExpect(status().isOk());
		
		mockMvc.perform(get("/hello/pathVariable/sseob"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string("hello sseob"))
		;
	}
	
	@Test
	public void helloTestMediaType() throws Exception{
		mockMvc.perform(
					get("/hello/mediaType")
							.contentType(MediaType.APPLICATION_JSON)
//							.accept(MediaType.APPLICATION_JSON) 
							// accept header가 없는 경우에는 어떤 데이터의 형태여도 받을 수 있다.
				)
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string("helloMediaType"))
		;
	}
	
	@Test
	public void helloHeaderTest() throws Exception{
		mockMvc.perform(
					get("/helloHeader")
							.header(HttpHeaders.AUTHORIZATION, "111")
				)
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string("helloHeader"))
		;
	}
	
	@Test
	public void helloParamTest() throws Exception{
		mockMvc.perform(
					get("/helloParam")
							.param("name", "sseob")
				)
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string("helloParam"))
		;
	}
}