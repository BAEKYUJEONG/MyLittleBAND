package com.web.blog.mapper;

import java.util.List;

import com.web.blog.dto.VideoBoard;

public interface FilterMapper {
	public List<VideoBoard> search(String keyword);
	public List<VideoBoard> filter(String genre, String color);
	
	
	
}