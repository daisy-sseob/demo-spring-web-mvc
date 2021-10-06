package me.sseob.demowebmvc;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice
public class GlobalController {
	/*
		Exception을 handling한다. Rest Api같은 경우는 ResponseEntity로 return하는걸 권장한다.
		status. 상태정보도 return해줘야할 필요가 있기 때문.
	 */
	@ExceptionHandler({EventException.class, RuntimeException.class})
	public String eventErrorHandler(RuntimeException e, Model model) {
		model.addAttribute("message", "Event error");
		return "error";
	}
	
	/*
		특정 controller에서 바인딩 또는 검증 설정을 변경하고 싶을 때 사용한다.
		custom한 validator를 추가하여 사용할 수 있다.
	 */
	@InitBinder
	public void initEventBinder(WebDataBinder webDataBinder) {
		webDataBinder.setDisallowedFields("id"); // 받고싶지 않은 field를 ignore처리할 수 있다. 바인딩 되지 않게.
		webDataBinder.addValidators(new EventValidator());
	}
	
	/*
		RequestMapping과 사용하지 않고 단독으로 사용시
	 *  모든 Method handler의 model객체에 아래 코드를 적용시킬 수 있다.
	 */
	@ModelAttribute
	public void categories(Model model) {
		model.addAttribute("categories", List.of("study", "seminar", "hobby", "social"));
	}
}
