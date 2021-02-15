package com.web.blog.dto;

public class OtherComment {
	private String img;
	private String content;
	
	public OtherComment() {
		
	}

	public OtherComment(String img, String content) {
		super();
		this.img = img;
		this.content = content;
	}


	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
