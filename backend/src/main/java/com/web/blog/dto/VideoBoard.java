package com.web.blog.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class VideoBoard {
	private String boardId;
	private String boardSubject;
	private String boardContent;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate boardDate;
	private String boardVideoUrl;
	private String boardThumbnail;
	private int boardView;
	private int boardLike;
	private String bandId;
	private String img;
	
	public VideoBoard() {

	}

	
	public VideoBoard(String boardId, String boardSubject, String boardContent, LocalDate boardDate,
			String boardVideoUrl, String boardThumbnail, int boardView, int boardLike, String bandId, String img) {
		this.boardId = boardId;
		this.boardSubject = boardSubject;
		this.boardContent = boardContent;
		this.boardDate = boardDate;
		this.boardVideoUrl = boardVideoUrl;
		this.boardThumbnail = boardThumbnail;
		this.boardView = boardView;
		this.boardLike = boardLike;
		this.bandId = bandId;
		this.img = img;
	}


	public String getBoardId() {
		return boardId;
	}

	public void setBoardId(String boardId) {
		this.boardId = boardId;
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

	public LocalDate getBoardDate() {
		return boardDate;
	}

	public void setBoardDate(LocalDate boardDate) {
		this.boardDate = boardDate;
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

	public int getBoardView() {
		return boardView;
	}

	public void setBoardView(int boardView) {
		this.boardView = boardView;
	}

	public int getBoardLike() {
		return boardLike;
	}

	public void setBoardLike(int boardLike) {
		this.boardLike = boardLike;
	}

	public String getBandId() {
		return bandId;
	}

	public void setBandId(String bandId) {
		this.bandId = bandId;
	}


	public String getImg() {
		return img;
	}


	public void setImg(String img) {
		this.img = img;
	}


	
	
	
}
