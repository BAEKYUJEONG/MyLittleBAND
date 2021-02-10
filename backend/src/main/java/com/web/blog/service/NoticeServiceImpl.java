package com.web.blog.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.blog.dao.NoticeDao;
import com.web.blog.dto.Notice;


@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	NoticeDao dao;
	
	@Override
	public int count() throws Exception {
		return dao.count();
	}

	@Override
	public List<Notice> searchAll(Map map) throws Exception {
		return dao.searchAll(map);
	}

	@Override
	public Notice retrieve(int noticeId) throws Exception {
		return dao.retrieve(noticeId);
	}

	@Override
	public List<Notice> searchTitle(Map map) throws Exception {
		return dao.searchTitle(map);
	}

	@Override
	public List<Notice> searchContent(Map map) throws Exception {
		return dao.searchContent(map);
	}

	@Override
	public int regist(Notice notice) throws Exception {
		return dao.regist(notice);
	}

	@Override
	public int modify(Notice notice) throws Exception {
		return dao.modify(notice);
	}

	@Override
	public int delete(int noticeId) throws Exception {
		return dao.delete(noticeId);
	}
	
}
