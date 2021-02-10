package com.web.blog.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

public class ShowReq {
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	private String bandId;
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime time;
	
	public ShowReq() {
	}

	public ShowReq(LocalDate date, String bandId, LocalTime time) {
		this.date = date;
		this.bandId = bandId;
		this.time = time;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getBandId() {
		return bandId;
	}

	public void setBandId(String bandId) {
		this.bandId = bandId;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	
	
	
	
}
