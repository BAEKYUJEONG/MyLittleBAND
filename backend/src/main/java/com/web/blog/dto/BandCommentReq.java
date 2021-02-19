package com.web.blog.dto;

public class BandCommentReq {
	private String commentId;
	private String memberId;
	private String content;
	private String img;
	
	public BandCommentReq() {
		
	}

	public BandCommentReq(String commentId, String memberId, String content, String img) {
		super();
		this.commentId = commentId;
		this.memberId = memberId;
		this.content = content;
		this.img = img;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
