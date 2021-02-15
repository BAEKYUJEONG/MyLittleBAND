package com.web.blog.dto;


public class VideoBoardReq {
	private String boardSubject;
	private String boardContent;
	private String boardVideoUrl;
	private String boardThumbnail;
	private String bandId;
	
	public VideoBoardReq() {

	}

	public VideoBoardReq(String boardSubject, String boardContent, String boardVideoUrl, String boardThumbnail,
			String bandId) {
		this.boardSubject = boardSubject;
		this.boardContent = boardContent;
		this.boardVideoUrl = boardVideoUrl;
		this.boardThumbnail = boardThumbnail;
		this.bandId = bandId;
	}

	public String getBoardSubject() {
		return boardSubject;
	}

	public void setBoardSubject(String boardSubject) {
		this.boardSubject = boardSubject;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public String getBoardVideoUrl() {
		return boardVideoUrl;
	}

	public void setBoardVideoUrl(String boardVideoUrl) {
		this.boardVideoUrl = boardVideoUrl;
	}

	public String getBoardThumbnail() {
		return boardThumbnail;
	}

	public void setBoardThumbnail(String boardThumbnail) {
		this.boardThumbnail = boardThumbnail;
	}

	public String getBandId() {
		return bandId;
	}

	public void setBandId(String bandId) {
		this.bandId = bandId;
	}

	

	
}
