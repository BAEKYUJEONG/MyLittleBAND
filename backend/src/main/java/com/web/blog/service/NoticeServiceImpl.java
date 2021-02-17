package com.web.blog.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.blog.dao.NoticeDao;
import com.web.blog.dto.Notice;
import com.web.blog.dto.NoticeReq;


@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	NoticeDao dao;

	@Override
	public void writenotice(NoticeReq req) {
		dao.writenotice(req);
	}

	@Override
	public Notice readnotice(String noticeId) {
		return dao.readnotice(noticeId);
	}

	@Override
	public void updatenotice(NoticeReq req, String noticeId) {
		dao.updatenotice(req, noticeId);
	}

	@Override
	public void deletenotice(String noticeId) {
		dao.deletenotice(noticeId);
	}

	@Override
	public int noticelist() {
		return dao.noticelist();
	}

	@Override
	public List<Notice> noticelistpage(int page) {
		return dao.noticelistpage(page);
	}
	
	
	
}
