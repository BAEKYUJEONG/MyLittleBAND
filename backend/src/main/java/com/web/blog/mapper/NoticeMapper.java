package com.web.blog.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.web.blog.dto.Notice;
import com.web.blog.dto.NoticeReq;

public interface NoticeMapper {
	public void writenotice(NoticeReq req);
	public Notice readnotice(String noticeId);
	public void updatenotice(NoticeReq req, String noticeId);
	public void deletenotice(String noticeId);
	public int noticelist();
	public List<Notice> noticelistpage(int page);
	
//	public int count() throws Exception;
//	
//	public List<Notice> selectAll(Map map) throws Exception;
//	
//	public Notice selectId(int noticeId) throws Exception;
//	public List<Notice> selectTitle(Map map) throws Exception;	
//	public List<Notice> selectContent(Map map) throws Exception;
//	
//	public int insert(Notice notice) throws Exception;
//	public int update(Notice notice) throws Exception;
//	public int delete(int noticeId) throws Exception;
}