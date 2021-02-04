package com.web.blog.dto;

public class Like {
	private String likeId;
	private String memberid;
	private String boardid;
	
	public Like() {
	}

	public Like(String likeId, String memberid, String boardid) {
		this.likeId = likeId;
		this.memberid = memberid;
		this.boardid = boardid;
	}

	public String getLikeId() {
		return likeId;
	}

	public void setLikeId(String likeId) {
		this.likeId = likeId;
	}

	public String getMemberid() {
		return memberid;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

	public String getBoardid() {
		return boardid;
	}

	public void setBoardid(String boardid) {
		this.boardid = boardid;
	}

	
}
