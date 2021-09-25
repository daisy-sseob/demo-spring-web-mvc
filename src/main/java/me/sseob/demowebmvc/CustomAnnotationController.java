package me.sseob.demowebmvc;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomAnnotationController {
	
	/*
		Meta Annotation인 @RequestMapping이 적용 되어있는 
		Custom Annotation @GetHelloMapping을 사용하여 요청 mapping하기 !
	 */
	@GetHelloMapping
	public String customAnnotationHello() {
		return "customAnnotationHello";
	}
}
