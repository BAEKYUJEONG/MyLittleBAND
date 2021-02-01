//package com.web.blog.dao;
//
//import java.util.List;
//import java.util.Map;
//
//import org.apache.ibatis.annotations.Mapper;
//
//import com.web.blog.dto.Notice;
//
//@Mapper
//public interface NoticeDao {
//	
//	public int count() throws Exception;
//	
//	public List<Notice> selectAll(Map map) throws Exception;
//	
//	public Notice selectId(String id) throws Exception;
//	public List<Notice> selectTitle(Map map) throws Exception;	
//	public List<Notice> selectContent(Map map) throws Exception;
//	
//	public int insert(Notice notice) throws Exception;
//	public int update(Notice notice) throws Exception;
//	public int delete(String id) throws Exception;
//}