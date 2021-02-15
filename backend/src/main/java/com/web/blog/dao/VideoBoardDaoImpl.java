package com.web.blog.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.blog.dto.VideoBoard;
import com.web.blog.dto.VideoBoardReq;
import com.web.blog.mapper.VideoBoardMapper;

@Repository
public class VideoBoardDaoImpl implements VideoBoardDao {
	@Autowired
	VideoBoardMapper mapper;

	@Override
	public void writevideo(VideoBoardReq req) {
		mapper.writevideo(req);
	}

	@Override
	public List<HashMap<String, String>> videoboardlist() {
		return mapper.videoboardlist();
	}

	@Override
	public VideoBoard readvideo(String boardId) {
		return mapper.readvideo(boardId);
	}

	@Override
	public void countup(String boardId) {
		mapper.countup(boardId);
	}

	@Override
	public void delvideo(String boardId) {
		mapper.delvideo(boardId);
	}

	@Override
	public void dellike(String boardId) {
		mapper.dellike(boardId);
	}

	@Override
	public void changevideo(String boardId, String boardSubject, String boardContent) {
		mapper.changevideo(boardId, boardSubject, boardContent);
	}

	@Override
	public void settime() {
		mapper.settime();
	}

	@Override
	public List<HashMap<String, String>> videoboardrankingview() {
		return mapper.videoboardrankingview();
	}

}
