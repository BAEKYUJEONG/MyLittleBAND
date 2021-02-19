package com.web.blog.service;



import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import com.web.blog.dto.BandShow;
import com.web.blog.dto.ShowListRes;

public interface ShowService {
	public BandShow getShowByTime(LocalDate date, LocalTime time);
	public void addshow(BandShow req);
	public void delshow(String showId);
	public List<ShowListRes> showlist(String bandId);
	public List<BandShow> dateshowlist(LocalDate date);
	public void updateshow(String showId, String title, String showContent);
	public List<BandShow> showTermList(String start, String end);
	public BandShow showDetail(String showId);
	
	
//	public BandShow getShowByTime1(LocalTime time);
}
