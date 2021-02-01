package com.web.blog.dto;

public class Comment {
	private String commentId;
	private String memberId;
	private String boardId;
	
	public Comment() {
		
	}

	public Comment(String commentId, String memberId, String boardId) {
		super();
		this.commentId = commentId;
		this.memberId = memberId;
		this.boardId = boardId;
	}

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getBoardId() {
		return boardId;
	}

	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}
	
}
