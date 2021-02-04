package com.web.blog.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.blog.dao.NoticeDao;
import com.web.blog.dto.Notice;


@Service
public class NoticeServiceImpl implements NoticeService {

	@Override
	public int count() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Notice> searchAll(Map map) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Notice retrieve(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Notice> searchTitle(Map map) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Notice> searchContent(Map map) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int regist(Notice notice) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int modify(Notice notice) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String id) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
/*
	@Autowired
	private NoticeDao dao;
	
	@Override
	public int count() throws Exception {
		return dao.count();
	}

	@Override
	public List<Notice> searchAll(Map map) throws Exception {
		return dao.selectAll(map);
	}

	@Override
	public Notice retrieve(String id) throws Exception {
		return dao.selectId(id);
	}

	@Override
	public List<Notice> searchTitle(Map map) throws Exception {
		return dao.selectTitle(map);
	}

	@Override
	public List<Notice> searchContent(Map map) throws Exception {
		return dao.selectContent(map);
	}

	@Override
	public int regist(Notice notice) throws Exception {
		return dao.insert(notice);
	}

	@Override
	public int modify(Notice notice) throws Exception {
		return dao.update(notice);
	}

	@Override
	public int delete(String id) throws Exception {
		return dao.delete(id);
	}
	*/
}
