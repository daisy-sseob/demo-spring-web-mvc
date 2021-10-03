package me.sseob.demowebmvc;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class Event {
	
	private Integer id;
	
	@NotBlank(message = "이름은 필수값 입니다.")
	private String name;
	
	@Min(value = 1, message = "limit 값은 1 이상이어야 합니다 ! ")
	private Integer limit;

	public Event() {
		
	}
	
	public Event(String name, Integer id) {
		this.name = name;
		this.id = id;
	}
	
	public Event(String name,Integer id, Integer limit) {
		this.id = id;
		this.name = name;
		this.limit = limit;
	}

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

	@Override
	public String toString() {
		return "Event{" +
				"id=" + id +
				", name='" + name + '\'' +
				", limit=" + limit +
				'}';
	}
}
