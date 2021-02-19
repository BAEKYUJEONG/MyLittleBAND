package com.web.blog.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class NoticeReq {
	private String noticeTitle;
	private String noticeContent;
	
	
	public NoticeReq() {
		
	}

	

	public NoticeReq(String noticeTitle, String noticeContent) {
		super();
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
	}



	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	
	
}
