package com.web.blog.dao;

import java.util.List;

import com.web.blog.dto.Calendar;

public interface CalendarDao {
	public List<Calendar> month(String bandId, String month);
}
