package com.web.blog.dto;

import io.swagger.annotations.ApiModelProperty;

public class BandOpened {
	
	@ApiModelProperty(example = "img")
	private String img;
	@ApiModelProperty(example = "김싸피")
	private String name;
	@ApiModelProperty(example = "안녕하세요")
	private String intro;
	
	
	public BandOpened() {
	}


	public BandOpened(String img, String name, String intro) {
		this.img = img;
		this.name = name;
		this.intro = intro;
	}


	public String getImg() {
		return img;
	}


	public void setImg(String img) {
		this.img = img;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getIntro() {
		return intro;
	}


	public void setIntro(String intro) {
		this.intro = intro;
	}

	
	
}
