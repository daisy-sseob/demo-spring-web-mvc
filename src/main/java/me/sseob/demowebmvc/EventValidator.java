package me.sseob.demowebmvc;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class EventValidator implements Validator {

	// 어떠한 domain class에 대한 validation을 지원하는가 ?
	@Override
	public boolean supports(Class<?> clazz) {
		return Event.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		Event event = (Event) target;
		if(event.getName().equalsIgnoreCase("sseob")){
			errors.rejectValue("name", "wrongValue","the value is not valid");
		}
	}
}
