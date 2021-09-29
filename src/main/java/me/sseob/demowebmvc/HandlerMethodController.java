package me.sseob.demowebmvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class HandlerMethodController {
	
	@GetMapping("/handlerMethodEvents/form")
	public String eventsForm(Model model) {
		Event event = new Event();
		event.setId(1);
		event.setName("sseob");
		event.setLimit(50);
		model.addAttribute("event", event);
		return "events/form";
	}

	@GetMapping("/handlerMethodEvents/{id}")
	@ResponseBody
	public Event handlerMethodHello(@PathVariable Integer id, @MatrixVariable String name) {
		Event event = new Event();
		event.setId(id);
		event.setName(name);
		return event;
	}
	
	/*
		RequestParam은 요청 매개변수로 이루어진 param들을 가져올 수 있다.
		요청 매개변수란 queryString, formdata와 같은 데이터를 일컫는다.
		생략도 할 수 있지만, 명시적으로 선언해주는것이 좋다.
	 */
	@PostMapping("/eventsRequestParam")
	@ResponseBody
	public Event eventsRequestParam(@RequestParam String name,
								 @RequestParam Integer limit) {
		Event event = new Event();
		event.setName(name);
		event.setLimit(limit);
		return event;
	}
	
	/*
		ModelAttribute로 복합객체 매핑하기 입니다.
		
		@ModelAttribute 매개변수 오른쪽에 BindingResult 매개변수를 사용하면
		Data Type이 달라서 mapping이 안되며 400 bad request, 실패하던 요청이
		실패되지 않으며 bindingResult로 에러를 핸들링 할 수 있다.
	 */
	@PostMapping("/eventsModelAttribute")
	public String eventsModelAttribute(@Validated @ModelAttribute Event event, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "/events/form";
		}
		return "/events/list";
	}
}
