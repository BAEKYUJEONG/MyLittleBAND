package com.web.blog.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

public class CalendarRes {
	private String calendarId;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate start;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate end;
	private Member[] member;
	private Band[] band;
	
	public CalendarRes() {
	}

	public CalendarRes(String calendarId, LocalDate start, LocalDate end, Member[] member, Band[] band) {
		this.calendarId = calendarId;
		this.start = start;
		this.end = end;
		this.member = member;
		this.band = band;
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

	public Member[] getMember() {
		return member;
	}

	public void setMember(Member[] member) {
		this.member = member;
	}

	public Band[] getBand() {
		return band;
	}

	public void setBand(Band[] band) {
		this.band = band;
	}

	

	
}
