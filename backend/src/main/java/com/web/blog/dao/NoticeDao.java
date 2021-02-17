package com.web.blog.dao;

import java.util.List;
import java.util.Map;

import com.web.blog.dto.Notice;
import com.web.blog.dto.NoticeReq;

public interface NoticeDao {

	public void writenotice(NoticeReq req);
	public Notice readnotice(String noticeId);
	public void updatenotice(NoticeReq req, String noticeId);
	public void deletenotice(String noticeId);
	public int noticelist();
	public List<Notice> noticelistpage(int page);
	
//	public int count() throws Exception;
//	
//	public List<Notice> searchAll(Map map) throws Exception;
//	
//	public Notice retrieve(int noticeId) throws Exception;
//	public List<Notice> searchTitle(Map map) throws Exception;
//	public List<Notice> searchContent(Map map) throws Exception;
//	
//	public int regist(Notice notice) throws Exception;
//	public int modify(Notice notice) throws Exception;
//	public int delete(int noticeId) throws Exception;
}