package com.web.blog.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

public class ShowListRes {
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	private String showId;
//	@DateTimeFormat(pattern = "HH:mm")
	private String time;
	private String title;
	private String showContent;
	
	public ShowListRes() {
	}

	public ShowListRes(LocalDate date, String showId, String time, String title, String showContent) {
		this.date = date;
		this.showId = showId;
		this.time = time;
		this.title = title;
		this.showContent = showContent;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getShowId() {
		return showId;
	}

	public void setShowId(String showId) {
		this.showId = showId;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getShowContent() {
		return showContent;
	}

	public void setShowContent(String showContent) {
		this.showContent = showContent;
	}

	
}
