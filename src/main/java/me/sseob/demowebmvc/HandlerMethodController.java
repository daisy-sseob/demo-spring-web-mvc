package me.sseob.demowebmvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/*
	SessionAttributes -> Model의 attribute중에 SessionAttributes에 설정된 key값과 같은 값이 있다면
	HttpSession에 넣어준다.
	
	개발자가 직접 HttpSession 객체에 값을 넣어주지 않아도 된다.
 */
@Controller
@SessionAttributes("event")
public class HandlerMethodController {
	
	@GetMapping("/events/form")
	public String eventsForm(Model model, HttpSession session) {
		Event event = new Event();
		event.setId(1);
		event.setName("sseob");
		event.setLimit(50);
		model.addAttribute("event", event);

//		session.setAttribute("event", event);
		return "events/form";
	}

	/*
		ModelAttribute로 복합객체 매핑하기 입니다.
		
		@ModelAttribute 매개변수 오른쪽에 BindingResult 매개변수를 사용하면
		Data Type이 달라서 mapping이 안되며 400 bad request, 실패하던 요청이
		실패되지 않으며 bindingResult로 에러를 핸들링 할 수 있다.
	 */
	@PostMapping("/events")
	public String createEvent(@Validated @ModelAttribute Event event,
	                          BindingResult bindingResult,
	                          SessionStatus sessionStatus) {
		
		//validation 실패했을 경우. error가 있을 경우
		if (bindingResult.hasErrors()) {
			return "/events/form";
		}

		//form 처리가 끝나고 session을 비운다.
		sessionStatus.setComplete();
		return "redirect:/events/list"; //redirect 시키기
	}

	/*
		Post Redirect Get 패턴
		post 요청 처리 후
		events/list view를 return하므로써 사용자가 브라우저에서 refresh를 했을때 다시 submit이 되지않게 한다.
	 */
	@GetMapping("/events/list")
	public String getEvents(Model model) {
		List<Event> eventList = new ArrayList<>();
		eventList.add(new Event("sseob !!", 10));
		model.addAttribute("eventList", eventList);
		return "/events/list";
	}
	
	/*
		PathVariable, MatrixVariable로 request parameter mapping하기
	 */
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
	
}
