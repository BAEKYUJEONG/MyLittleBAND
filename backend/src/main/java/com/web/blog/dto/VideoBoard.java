package com.web.blog.dto;

public class VideoBoard {
	private String boardId;
	private String boardSubject;
	private String boardContent;
	private int boardDate;
	private String boardVideoUrl;
	private String boardThumbnail;
	private int boardView;
	private int boardLike;
	private String bandId;
	
	public VideoBoard() {

	}

	public VideoBoard(String boardId, String boardSubject, String boardContent, int boardDate, String boardVideoUrl,
			String boardThumbnail, int boardView, int boardLike, String bandId) {
		super();
		this.boardId = boardId;
		this.boardSubject = boardSubject;
		this.boardContent = boardContent;
		this.boardDate = boardDate;
		this.boardVideoUrl = boardVideoUrl;
		this.boardThumbnail = boardThumbnail;
		this.boardView = boardView;
		this.boardLike = boardLike;
		this.bandId = bandId;
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

	public int getBoardDate() {
		return boardDate;
	}

	public void setBoardDate(int boardDate) {
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
	
}
