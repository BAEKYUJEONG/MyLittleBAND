package com.web.blog.service;

import java.util.HashMap;
import java.util.List;

import com.web.blog.dto.Calendar;

public interface CalendarService {
	public List<Calendar> month(String bandId, String month);
	public List<HashMap<String, String>> bandcalendar(String bandId);
	public Calendar bandcalendardetail(String bandId, String calendarId);
	public void bandcalendaradd(String bandId, Calendar req);
	public void bandcalendarupdate(String bandId, String calendarId, Calendar req);
	public void bandcalendardel(String bandId, String calendarId);
}
