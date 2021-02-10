package com.web.blog.dao;



import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.blog.dto.BandShow;
import com.web.blog.dto.ShowListRes;
import com.web.blog.mapper.ShowMapper;


@Repository
public class ShowDaoImpl implements ShowDao {
	@Autowired
	ShowMapper mapper;

	@Override
	public BandShow getShowByTime(LocalDate date, LocalTime time) {
		return mapper.getShowByTime(date,time);
	}

	@Override
	public void addshow(BandShow req) {
		mapper.addshow(req);
	}

	@Override
	public void delshow(String showId) {
		mapper.delshow(showId);
	}

	@Override
	public List<ShowListRes> showlist(String bandId) {
		return mapper.showlist(bandId);
	}

	@Override
	public List<BandShow> dateshowlist(LocalDate date) {
		return mapper.dateshowlist(date);
	}

	@Override
	public void updateshow(String showId, String title, String showContent) {
		mapper.updateshow(showId, title, showContent);
	}

//	@Override
//	public BandShow getShowByTime1(LocalTime time) {
//		// TODO Auto-generated method stub
//		return mapper.getShowByTime1(time);
//	}
	
}
