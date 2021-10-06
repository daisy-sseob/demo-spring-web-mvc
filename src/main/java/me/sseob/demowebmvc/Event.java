package me.sseob.demowebmvc;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Event {
	
	private Integer id;
	
	@NotBlank(message = "이름은 필수값 입니다.")
	private String name;
	
	@Min(value = 1, message = "limit 값은 1 이상이어야 합니다 ! ")
	private Integer limit;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate startDate;

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

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
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
