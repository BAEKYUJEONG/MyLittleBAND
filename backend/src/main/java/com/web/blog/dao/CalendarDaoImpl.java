package com.web.blog.dao;


import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.blog.dto.Calendar;
import com.web.blog.mapper.CalendarMapper;




@Repository
public class CalendarDaoImpl implements CalendarDao {
	@Autowired
	CalendarMapper mapper;

	@Override
	public List<Calendar> month(String bandId, String month) {
		return mapper.month(bandId, month);
	}

	@Override
	public List<HashMap<String, String>> bandcalendar(String bandId) {
		return mapper.bandcalendar(bandId);
	}

	@Override
	public Calendar bandcalendardetail(String bandId, String calendarId) {
		return mapper.bandcalendardetail(bandId, calendarId);
	}

	@Override
	public void bandcalendaradd(String bandId, Calendar req) {
		mapper.bandcalendaradd(bandId, req);
	}

	@Override
	public void bandcalendarupdate(String bandId, String calendarId, Calendar req) {
		mapper.bandcalendarupdate(bandId, calendarId, req);
	}

	@Override
	public void bandcalendardel(String bandId, String calendarId) {
		mapper.bandcalendardel(bandId, calendarId);
	}


	
}
