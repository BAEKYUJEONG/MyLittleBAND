package com.web.blog.dto;

public class BandComment {
	private String commentId;
	private String bandboardId;
	private String memberId;
	private String content;
	
	public BandComment() {
		
	}

	public BandComment(String commentId, String bandboardId, String memberId, String content) {
		super();
		this.commentId = commentId;
		this.bandboardId = bandboardId;
		this.memberId = memberId;
		this.content = content;
	}

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public String getbandboardId() {
		return bandboardId;
	}

	public void setbandboardId(String bandboardId) {
		this.bandboardId = bandboardId;
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
