package com.web.blog.dto;

public class Calendar {
	private String calendarId;
	private String date;
	private String content;
	private String bandId;
	private String photo;
	private String time;
	private String title;
	
	public Calendar() {
	}

	public Calendar(String calendarId, String date, String content, String bandId, String photo, String time,
			String title) {
		this.calendarId = calendarId;
		this.date = date;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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

	
}
