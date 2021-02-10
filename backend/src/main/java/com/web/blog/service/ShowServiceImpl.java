package com.web.blog.service;



import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.blog.dao.ShowDao;
import com.web.blog.dto.BandShow;
import com.web.blog.dto.ShowListRes;

@Service
public class ShowServiceImpl implements ShowService{
	@Autowired
	ShowDao dao;

	@Override
	public BandShow getShowByTime(LocalDate date, LocalTime time) {
		return dao.getShowByTime(date,time);
	}

	@Override
	public void addshow(BandShow req) {
		dao.addshow(req);
	}

	@Override
	public void delshow(String showId) {
		dao.delshow(showId);
	}

	@Override
	public List<ShowListRes> showlist(String bandId) {
		return dao.showlist(bandId);
	}

	@Override
	public List<BandShow> dateshowlist(LocalDate date) {
		return dao.dateshowlist(date);
	}

	@Override
	public void updateshow(String showId, String title, String showContent) {
		dao.updateshow(showId, title, showContent);
	}

//	@Override
//	public BandShow getShowByTime1(LocalTime time) {
//		// TODO Auto-generated method stub
//		return dao.getShowByTime1(time);
//	}

}
