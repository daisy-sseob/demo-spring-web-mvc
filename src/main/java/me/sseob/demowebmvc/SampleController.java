package me.sseob.demowebmvc;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class SampleController {

	/*
	@RequestMapping(value = "/hello",method = {RequestMethod.GET, RequestMethod.POST})
	HTTP Method GET, POST둘다 허용하지 않고 GET만 허용 하고싶다면 @GetMapping으로 줄여서 사용할 수 있다.
	
	"/hello/**" -> ** asterisk 두개를 작성해주면 path 상관없이 뒤에 모든 URI를 mapping한다. 
	*/
	@GetMapping("/hello")
	@ResponseBody
	public String hello() {
		return "hello";
	}

	/**
	 * PathVariable + 정규식으로 URI에서 parameter 얻기.
	 * @param name
	 * @return
	 */
	@GetMapping("/pathVariable/{name:[a-z]+}")
	@ResponseBody
	public String hello(@PathVariable String name) {
		return "hello " + name;
	}
	
	@GetMapping(
			value = "/hello/mediaType"
			, consumes = {MediaType.APPLICATION_JSON_VALUE}
			, produces = MediaType.TEXT_PLAIN_VALUE
	)
	@ResponseBody
	public String helloMediaType() {
		return "helloMediaType";
	}
	
	@GetMapping(
			value = "/helloHeader"
//			, headers ="!" + HttpHeaders.FROM // ! -> not을 통해 해당 header가 없는 경우에만 mapping하도록 설정할 수 있다.
			, headers = HttpHeaders.AUTHORIZATION + "=111" // 요청 header의 value까지 정확히 일치할 경우에만 mapping하도록 설정할 수 있다.
	)
	public @ResponseBody String helloHeader() {
		return "helloHeader";
	}
	
	// name이라는 param이 있을 경우에만 mapping
	// 요청 header와 마찬가지로 param value까지 명시해주면 value값까지 일치하도록 설정 가능하다.  
	@GetMapping(value = "/helloParam", params = "name")
	public @ResponseBody String helloParam() {
		return "helloParam";
	}
}
