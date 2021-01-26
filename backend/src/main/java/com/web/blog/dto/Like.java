package com.web.blog.dto;

public class Like {
	private String id;
	private String memberid;
	private String boardid;
	
	public Like() {
	}

	public Like(String id, String memberid, String boardid) {
		this.id = id;
		this.memberid = memberid;
		this.boardid = boardid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
