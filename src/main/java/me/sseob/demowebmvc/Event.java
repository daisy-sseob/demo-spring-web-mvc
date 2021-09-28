package me.sseob.demowebmvc;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class Event {
	
	interface ValidateLimit{}
	interface ValidateName{}
	
	private Integer id;
	
	@NotBlank(groups = ValidateName.class, message = "이름은 필수 입력값 입니다.")
	private String name;
	
	@Min(value = 1, message = "최소값은 1 입니다.", groups = ValidateLimit.class)
	private Integer limit;

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
