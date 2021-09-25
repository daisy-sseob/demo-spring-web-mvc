package me.sseob.demowebmvc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest
public class HeadAndOptionsControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	
	/*
		HTTP metod HEAD 요청은 spring에 미리 구현되어있는 기능에 의해
		응답 본문을 response하지 않는다.
	 */
	@Test
	public void requestHeadTest() throws Exception{
		mockMvc.perform(head("/requestHead"))
				.andDo(print())
				.andExpect(status().isOk())
		;
	}
	
	/*
		OPTIONS Method는 요청 URI에 대해 사용할 수 있는 HTTP Method를 제공한다.
		응답 헤더에 Allow http mehtod를 담아 응답한다.
		HEAD method와 마찬가지로 body는 비어있음 !
	 */
	@Test
	public void requestOptionsTest() throws Exception{
		mockMvc.perform(options("/requestHead"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(header().exists(HttpHeaders.ALLOW))
				.andExpect(header().stringValues(HttpHeaders.ALLOW,hasItems(
						containsString("GET")
						,containsString("POST")
						,containsString("HEAD")
						,containsString("OPTIONS")
				)))
		;
	}
}