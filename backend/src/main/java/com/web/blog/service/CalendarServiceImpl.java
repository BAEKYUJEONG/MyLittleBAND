package com.web.blog.service;



import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.blog.dao.CalendarDao;
import com.web.blog.dto.Calendar;


@Service
public class CalendarServiceImpl implements CalendarService{
	@Autowired
	CalendarDao dao;

	@Override
	public List<Calendar> month(String bandId, String month) {
		return dao.month(bandId, month);
	}

	@Override
	public List<HashMap<String, String>> bandcalendar(String bandId) {
		return dao.bandcalendar(bandId);
	}

	@Override
	public Calendar bandcalendardetail(String bandId, String calendarId) {
		return dao.bandcalendardetail(bandId, calendarId);
	}

	@Override
	public void bandcalendaradd(String bandId, Calendar req) {
		dao.bandcalendaradd(bandId, req);
	}

	@Override
	public void bandcalendarupdate(String bandId, String calendarId, Calendar req) {
		dao.bandcalendarupdate(bandId, calendarId, req);
	}

	@Override
	public void bandcalendardel(String bandId, String calendarId) {
		dao.bandcalendardel(bandId, calendarId);
	}
	
	

}
