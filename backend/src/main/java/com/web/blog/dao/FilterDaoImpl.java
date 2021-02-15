package com.web.blog.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.blog.dto.VideoBoard;
import com.web.blog.mapper.FilterMapper;

@Repository
public class FilterDaoImpl implements FilterDao {
	@Autowired
	FilterMapper mapper;

	@Override
	public List<VideoBoard> search(String keyword) {
		return mapper.search(keyword);
	}

	@Override
	public List<VideoBoard> filter(String genre, String color) {
		return mapper.filter(genre, color);
	}
	
	
	
}
