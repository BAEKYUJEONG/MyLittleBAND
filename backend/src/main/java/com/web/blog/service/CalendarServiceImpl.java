package com.web.blog.service;



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
	
	

}
