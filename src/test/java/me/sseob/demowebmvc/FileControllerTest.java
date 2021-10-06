package me.sseob.demowebmvc;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FileControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	
	// mock test file 만들어서 테스트 해보자 !
	@Test
	public void fileUploadTest() throws Exception {
		MockMultipartFile file = new MockMultipartFile("file",
				"test.txt",
				MediaType.TEXT_PLAIN_VALUE,
				"hello file".getBytes(StandardCharsets.UTF_8)
		);
		mockMvc.perform(
					multipart("/file").file(file)
				).andDo(print())
				.andExpect(status().is3xxRedirection())
		;
	}
}