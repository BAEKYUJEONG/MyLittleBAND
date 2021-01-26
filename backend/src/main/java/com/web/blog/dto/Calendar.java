package com.web.blog.dto;

public class Calendar {
	private String date;
	private String content;
	private String bandid;
	private String photo;
	private String time;
	private String title;
	
	public Calendar() {
	}

	public Calendar(String date, String content, String bandid, String photo, String time, String title) {
		this.date = date;
		this.content = content;
		this.bandid = bandid;
		this.photo = photo;
		this.time = time;
		this.title = title;
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

	public String getBandid() {
		return bandid;
	}

	public void setBandid(String bandid) {
		this.bandid = bandid;
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
