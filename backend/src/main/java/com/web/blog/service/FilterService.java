package com.web.blog.service;

import java.util.List;

import com.web.blog.dto.VideoBoard;

public interface FilterService {
	public List<VideoBoard> search(String keyword);
	public List<VideoBoard> filter(String genre, String color);
	
	
	
	
}
