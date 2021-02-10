package com.web.blog.dao;

import java.util.List;
import java.util.Map;

import com.web.blog.dto.Notice;

public interface NoticeDao {

	
	public int count() throws Exception;
	
	public List<Notice> searchAll(Map map) throws Exception;
	
	public Notice retrieve(int noticeId) throws Exception;
	public List<Notice> searchTitle(Map map) throws Exception;
	public List<Notice> searchContent(Map map) throws Exception;
	
	public int regist(Notice notice) throws Exception;
	public int modify(Notice notice) throws Exception;
	public int delete(int noticeId) throws Exception;
}