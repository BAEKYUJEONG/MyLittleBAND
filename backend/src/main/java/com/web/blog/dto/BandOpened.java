package com.web.blog.dto;

import io.swagger.annotations.ApiModelProperty;

public class BandOpened {
	
	@ApiModelProperty(example = "img")
	private String img;
	@ApiModelProperty(example = "김싸피")
	private String name;
	@ApiModelProperty(example = "안녕하세요")
	private String intro;
	private String genre;
	private String color;
	
	
	public BandOpened() {
	}


	public BandOpened(String img, String name, String intro, String genre, String color) {
		this.img = img;
		this.name = name;
		this.intro = intro;
		this.genre = genre;
		this.color = color;
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


	public String getGenre() {
		return genre;
	}


	public void setGenre(String genre) {
		this.genre = genre;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	
	
}
