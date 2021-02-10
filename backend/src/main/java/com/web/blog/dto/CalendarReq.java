package com.web.blog.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

public class CalendarReq {
	private String title;
	private String content;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate start;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate end;
	private Member[] member;
	private Band[] band;
	
	public CalendarReq() {
	}

	public CalendarReq(String title, String content, LocalDate start, LocalDate end, Member[] member, Band[] band) {
		this.title = title;
		this.content = content;
		this.start = start;
		this.end = end;
		this.member = member;
		this.band = band;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
