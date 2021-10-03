package me.sseob.demowebmvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.time.LocalDateTime;
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
	
	@GetMapping("/events/form/name")
	public String eventsFormName(Model model) {
		model.addAttribute("event", new Event());
		return "events/form-name";
	}

	/*
		ModelAttribute로 복합객체 매핑하기 입니다.
		@ModelAttribute는 session 객체도 바인딩 받는다.
		@ModelAttribute 매개변수 오른쪽에 BindingResult 매개변수를 사용하면
		Data Type이 달라서 mapping이 안되며 400 bad request, 실패하던 요청이
		실패되지 않으며 bindingResult로 에러를 핸들링 할 수 있다.
	 */
	@PostMapping("/events/form/name")
	public String createFormNameSubmit(@Validated @ModelAttribute Event event,
	                          BindingResult bindingResult) {
		
		//validation 실패했을 경우. error가 있을 경우
		if (bindingResult.hasErrors()) {
			return "/events/form-name";
		}
		return "redirect:/events/form/limit"; //redirect 시키기
	}
	
	@GetMapping("/events/form/limit")
	public String eventsFormLimit(@ModelAttribute Event event, Model model) {
		model.addAttribute("event", event);
		return "events/form-limit";
	}

	@PostMapping("/events/form/limit")
	public String createFormLimitSubmit(@Validated @ModelAttribute Event event,
	                                    BindingResult bindingResult,
	                                    SessionStatus sessionStatus,
	                                    Model model) {
		if (bindingResult.hasErrors()) {
			return "/events/form-limit";
		}
		
		sessionStatus.setComplete(); // submit이 모두 완료되면 session 비우기.
		
		model.addAttribute("name", event.getName());
		model.addAttribute("limit", event.getLimit());
		return "redirect:/events/list";
	}

	/*
		Post Redirect Get 패턴
		post 요청 처리 후
		events/list view를 return하므로써 사용자가 브라우저에서 refresh를 했을때 다시 submit이 되지않게 한다.
	 */
	@GetMapping("/events/list")
	public String getEvents(Model model, @SessionAttribute LocalDateTime visitTime) {
		System.out.println(visitTime);
		
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
