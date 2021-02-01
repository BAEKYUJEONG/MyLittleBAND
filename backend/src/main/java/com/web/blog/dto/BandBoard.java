package com.web.blog.dto;

public class BandBoard {
	private String id;
	private String content;
	private String date;
	private String view;
	private String bandid;
	
	public BandBoard() {
	}

	public BandBoard(String id, String content, String date, String view, String bandid) {
		this.id = id;
		this.content = content;
		this.date = date;
		this.view = view;
		this.bandid = bandid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	public String getBandid() {
		return bandid;
	}

	public void setBandid(String bandid) {
		this.bandid = bandid;
	}
	
	
	
}
