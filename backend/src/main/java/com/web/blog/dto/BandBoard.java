package com.web.blog.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class BandBoard {
	private String bandBoardId;
	private String title;
	private String content;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	private String view;
	private String memberId;
	private String bandId;
	
	public BandBoard() {
	}

	public BandBoard(String bandBoardId, String title, String content, LocalDate date, String view, String memberId,
			String bandId) {
		this.bandBoardId = bandBoardId;
		this.title = title;
		this.content = content;
		this.date = date;
		this.view = view;
		this.memberId = memberId;
		this.bandId = bandId;
	}

	public String getBandBoardId() {
		return bandBoardId;
	}

	public void setBandBoardId(String bandBoardId) {
		this.bandBoardId = bandBoardId;
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

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getBandId() {
		return bandId;
	}

	public void setBandId(String bandId) {
		this.bandId = bandId;
	}

	
	
}
