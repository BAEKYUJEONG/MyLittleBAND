package com.web.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.blog.dao.BandBoardDao;
import com.web.blog.dto.BandBoard;
import com.web.blog.dto.BandBoardReq;

@Service
public class BandBoardServiceImpl implements BandBoardService{
	@Autowired
	BandBoardDao dao;

	@Override
	public void writebandboard(BandBoardReq req) {
		dao.writebandboard(req);
	}

	@Override
	public List<BandBoard> bandboardlist(String bandId) {
		return dao.bandboardlist(bandId);
	}

	@Override
	public void settime() {
		dao.settime();
	}

	@Override
	public BandBoard readbandboard(String bandboardId) {
		return dao.readbandboard(bandboardId);
	}

	@Override
	public void countup(String bandboardId) {
		dao.countup(bandboardId);
	}

	@Override
	public void delbandboard(String bandboardId) {
		dao.delbandboard(bandboardId);
	}

	@Override
	public void changebandboard(String bandboardId, String title, String content) {
		dao.changebandboard(bandboardId, title, content);
	}
	
	

}
