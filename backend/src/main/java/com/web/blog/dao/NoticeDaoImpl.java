package com.web.blog.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.blog.dto.Notice;
import com.web.blog.dto.NoticeReq;
import com.web.blog.mapper.NoticeMapper;

@Repository
public class NoticeDaoImpl implements NoticeDao{
	@Autowired
	NoticeMapper mapper;

	@Override
	public void writenotice(NoticeReq req) {
		mapper.writenotice(req);
	}

	@Override
	public Notice readnotice(String noticeId) {
		return mapper.readnotice(noticeId);
	}

	@Override
	public void updatenotice(NoticeReq req, String noticeId) {
		mapper.updatenotice(req, noticeId);
	}

	@Override
	public void deletenotice(String noticeId) {
		mapper.deletenotice(noticeId);
	}

	@Override
	public int noticelist() {
		return mapper.noticelist();
	}

	@Override
	public List<Notice> noticelistpage(int page) {
		return mapper.noticelistpage(page);
	}


}
