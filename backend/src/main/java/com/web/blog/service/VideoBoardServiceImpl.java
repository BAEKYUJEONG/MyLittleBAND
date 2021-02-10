package com.web.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.blog.dao.VideoBoardDao;
import com.web.blog.dto.VideoBoard;

@Service
public class VideoBoardServiceImpl implements VideoBoardService{
	
	@Autowired
	VideoBoardDao dao;

	@Override
	public int insert(VideoBoard vboard) throws Exception {
		return dao.insert(vboard);
	}

	@Override
	public int update(VideoBoard vboard) throws Exception {
		return dao.update(vboard);
	}

	@Override
	public int delete(String boardId) throws Exception {
		return dao.delete(boardId);
	}

	@Override
	public VideoBoard select(String boardId) throws Exception {
		return dao.select(boardId);
	}

	@Override
	public List<VideoBoard> selectAll() throws Exception {
		return dao.selectAll();
	}
}
