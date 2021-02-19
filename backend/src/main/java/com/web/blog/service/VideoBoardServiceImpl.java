package com.web.blog.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.blog.dao.VideoBoardDao;
import com.web.blog.dto.VideoBoard;
import com.web.blog.dto.VideoBoardReq;

@Service
public class VideoBoardServiceImpl implements VideoBoardService{
	@Autowired
	VideoBoardDao dao;

	@Override
	public void writevideo(VideoBoardReq req) {
		dao.writevideo(req);
	}

	@Override
	public List<HashMap<String, String>> videoboardlist() {
		return dao.videoboardlist();
	}

	@Override
	public HashMap<String, String> readvideo(String boardId) {
		return dao.readvideo(boardId);
	}

	@Override
	public void countup(String boardId) {
		dao.countup(boardId);
	}

	@Override
	public void delvideo(String boardId) {
		dao.delvideo(boardId);
	}

	@Override
	public void dellike(String boardId) {
		dao.dellike(boardId);
	}

	@Override
	public void changevideo(String boardId, String boardSubject, String boardContent) {
		dao.changevideo(boardId, boardSubject, boardContent);
	}

	@Override
	public void settime() {
		dao.settime();
	}

	@Override
	public List<HashMap<String, String>> videoboardrankingview() {
		return dao.videoboardrankingview();
	}

	@Override
	public List<HashMap<String, String>> videoboardrankingpopular() {
		return dao.videoboardrankingpopular();
	}
	
	

}
