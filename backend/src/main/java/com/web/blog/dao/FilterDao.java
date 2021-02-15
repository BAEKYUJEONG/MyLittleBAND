package com.web.blog.dao;

import java.util.List;

import com.web.blog.dto.VideoBoard;

public interface FilterDao {
	public List<VideoBoard> search(String keyword);
	public List<VideoBoard> filter(String genre, String color);
	
	
	
}
