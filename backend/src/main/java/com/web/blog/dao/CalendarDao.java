package com.web.blog.dao;

import java.util.HashMap;
import java.util.List;

import com.web.blog.dto.Calendar;
import com.web.blog.dto.CalendarReq;
import com.web.blog.dto.CalendarRes;

public interface CalendarDao {
	public List<Calendar> month(String bandId, String month);
	public List<HashMap<String, String>> bandcalendar(String bandId);
	public CalendarRes bandcalendardetail(String bandId, String calendarId);
	public void bandcalendaradd(String bandId, CalendarReq req);
	public void bandcalendarupdate(String bandId, String calendarId, CalendarReq req);
	public void bandcalendardel(String bandId, String calendarId);
}
