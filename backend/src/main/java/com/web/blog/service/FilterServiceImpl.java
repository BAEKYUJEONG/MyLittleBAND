package com.web.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.blog.dao.FilterDao;
import com.web.blog.dto.VideoBoard;

@Service
public class FilterServiceImpl implements FilterService{
	@Autowired
	FilterDao dao;

	@Override
	public List<VideoBoard> search(String keyword) {
		return dao.search(keyword);
	}

	@Override
	public List<VideoBoard> filter(String genre, String color) {
		return dao.filter(genre, color);
	}

}
