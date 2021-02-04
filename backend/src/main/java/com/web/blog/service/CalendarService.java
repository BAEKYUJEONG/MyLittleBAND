package com.web.blog.service;

import java.util.List;

import com.web.blog.dto.Calendar;

public interface CalendarService {
	public List<Calendar> month(String bandId, String month);
}
