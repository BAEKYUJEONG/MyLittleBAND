package com.web.blog.mapper;

import java.util.List;

import com.web.blog.dto.Calendar;

public interface CalendarMapper {
	public List<Calendar> month(String bandId, String month);
}