package me.sseob.demowebmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("hello")
public class SampleController {

	/*
	@RequestMapping(value = "/hello",method = {RequestMethod.GET, RequestMethod.POST})
	HTTP Method GET, POST둘다 허용하지 않고 GET만 허용 하고싶다면 @GetMapping으로 줄여서 사용할 수 있다.
	
	"/hello/**" -> ** asterisk 두개를 작성해주면 path 상관없이 뒤에 모든 URI를 mapping한다. 
	*/
	@GetMapping("/**")
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
}
