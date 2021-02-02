package com.web.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.web.blog.dao.VideoBoardDao;
import com.web.blog.dto.VideoBoard;

public class VideoBoardServiceImpl implements VideoBoardService{

	@Autowired
	private VideoBoardDao dao;
	
	@Override
	public int regist(VideoBoard video) throws Exception {
		return dao.insert(video);
	}

	@Override
	public int modify(VideoBoard video) throws Exception {
		return dao.update(video);
	}

	@Override
	public int remove(String boardId) throws Exception {
		return dao.delete(boardId);
	}

	@Override
	public List<VideoBoard> searchAll() throws Exception {
		return dao.selectAll();
	}

}
