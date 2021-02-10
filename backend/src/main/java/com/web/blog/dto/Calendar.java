package com.web.blog.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

public class Calendar {
	private String calendarId;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate start;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate end;
	private String content;
	private String bandId;
	private String photo;
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime time;
	private String title;
	
	public Calendar() {
	}

	public Calendar(String calendarId, LocalDate start, LocalDate end, String content, String bandId, String photo,
			LocalTime time, String title) {
		this.calendarId = calendarId;
		this.start = start;
		this.end = end;
		this.content = content;
		this.bandId = bandId;
		this.photo = photo;
		this.time = time;
		this.title = title;
	}

	public String getCalendarId() {
		return calendarId;
	}

	public void setCalendarId(String calendarId) {
		this.calendarId = calendarId;
	}

	public LocalDate getStart() {
		return start;
	}

	public void setStart(LocalDate start) {
		this.start = start;
	}

	public LocalDate getEnd() {
		return end;
	}

	public void setEnd(LocalDate end) {
		this.end = end;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getBandId() {
		return bandId;
	}

	public void setBandId(String bandId) {
		this.bandId = bandId;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
}
