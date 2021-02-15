package com.web.blog.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.blog.dto.BandBoard;
import com.web.blog.dto.BandBoardReq;
import com.web.blog.mapper.BandBoardMapper;

@Repository
public class BandBoardDaoImpl implements BandBoardDao {
	@Autowired
	BandBoardMapper mapper;

	@Override
	public void writebandboard(BandBoardReq req) {
		mapper.writebandboard(req);
	}

	@Override
	public List<BandBoard> bandboardlist() {
		return mapper.bandboardlist();
	}

	@Override
	public void settime() {
		mapper.settime();
	}

	@Override
	public BandBoard readbandboard(String bandboardId) {
		return mapper.readbandboard(bandboardId);
	}

	@Override
	public void countup(String bandboardId) {
		mapper.countup(bandboardId);
	}

	@Override
	public void delbandboard(String bandboardId) {
		mapper.delbandboard(bandboardId);
	}

	@Override
	public void changebandboard(String bandboardId, String title, String content) {
		mapper.changebandboard(bandboardId, title, content);
	}
	
	
}
