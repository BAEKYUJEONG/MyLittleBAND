package com.web.blog.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

public class BandShowlist {
	private String showId;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	private String title;
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime time;
	private String showContent;
	private String img;
	private String bandId;
	private String name;
	private String shows;
	
	public BandShowlist() {
	}

	


	



	public BandShowlist(String showId, LocalDate date, String title, LocalTime time, String showContent, String img,
			String bandId, String name, String shows) {
		super();
		this.showId = showId;
		this.date = date;
		this.title = title;
		this.time = time;
		this.showContent = showContent;
		this.img = img;
		this.bandId = bandId;
		this.name = name;
		this.shows = shows;
	}








	public String getShowId() {
		return showId;
	}

	public void setShowId(String showId) {
		this.showId = showId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}


	public String getShowContent() {
		return showContent;
	}

	public void setShowContent(String showContent) {
		this.showContent = showContent;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getBandId() {
		return bandId;
	}

	public void setBandId(String bandId) {
		this.bandId = bandId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}








	public String getShows() {
		return shows;
	}








	public void setShows(String shows) {
		this.shows = shows;
	}

	
}
