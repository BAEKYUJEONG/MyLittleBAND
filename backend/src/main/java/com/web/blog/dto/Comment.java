package com.web.blog.dto;

public class Comment {
	private String commentId;
	private String boardId;
	private String memberId;
	private String content;
	
	public Comment() {
		
	}

	public Comment(String commentId, String boardId, String memberId, String content) {
		super();
		this.commentId = commentId;
		this.boardId = boardId;
		this.memberId = memberId;
		this.content = content;
	}

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public String getBoardId() {
		return boardId;
	}

	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
