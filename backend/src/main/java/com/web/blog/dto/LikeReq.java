package com.web.blog.dto;

import io.swagger.annotations.ApiModelProperty;

public class LikeReq {
	@ApiModelProperty(example = "1")
	private String memberId;
	@ApiModelProperty(example = "5")
	private String boardId;
	
	public LikeReq() {
	}

	public LikeReq(String memberId, String boardId) {
		this.memberId = memberId;
		this.boardId = boardId;
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
