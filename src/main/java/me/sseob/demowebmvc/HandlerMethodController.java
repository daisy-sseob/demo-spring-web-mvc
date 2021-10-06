package me.sseob.demowebmvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

		throw new EventException();
		
//		model.addAttribute("event", new Event());
//		return "events/form-name";
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


	/*
	 * RedirectAttributes를 사용하면 redirect할 때의 query param으로 전달하려고 하는 데이터를 명시할 수 있다. 
	 * queryString으로 전달해야되기 때문에 String 으로 변환 가능한 값만 사용할 수 있다.
	 */
	@PostMapping("/events/form/limit")
	public String createFormLimitSubmit(@Validated @ModelAttribute Event event,
	                                    BindingResult bindingResult,
	                                    SessionStatus sessionStatus,
	                                    RedirectAttributes attributes) {
		if (bindingResult.hasErrors()) {
			return "/events/form-limit";
		}
		
		sessionStatus.setComplete(); // submit이 모두 완료되면 session 비우기.
		
//		attributes.addAttribute("name", event.getName());
//		attributes.addAttribute("limit", event.getLimit());
		
		/*
			Flash Attributes redirect하기전에 데이터를 Http 세션에 저장하고 redirect를 요청 처리후 즉시 세션을 제거한다.
			RedirectAttributes는 Event class같은 복합 객체를 전달하는데 어려움이 있지만 Flash Attributes는 복합 객체도 가능하다.
			redirec된 method의 Model 객체로 바로 매핑되기 때문에 따로 mapping하기위한 부가작업이 필요없다.
			session을 통해 전달되기 때문에 uri에 데이터가 노출되지 않는다.
		 */
		attributes.addFlashAttribute("newEvent", event);
		
		return "redirect:/events/list";
	}

	/*
		Post Redirect Get 패턴
		post 요청 처리 후
		events/list view를 return하므로써 사용자가 브라우저에서 refresh를 했을때 다시 submit이 되지않게 한다.
	 */
	@GetMapping("/events/list")
	public String getEvents(
	        Model model,
	        @SessionAttribute LocalDateTime visitTime //SessionAttribute를 이용하여 session에서 visitTime을 매핑.
	) {
		System.out.println(visitTime);

		//FlashAttributes를 이용했을 경우 Model객체에 바로 매핑된다. 사용시 캐스팅해서 사용해야됨.
		Event newEvent = (Event) model.asMap().get("newEvent");

		List<Event> eventList = new ArrayList<>();
		eventList.add(new Event("sseob !!", 10));
		eventList.add(newEvent);
		
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
