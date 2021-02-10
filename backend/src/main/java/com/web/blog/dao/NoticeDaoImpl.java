package com.web.blog.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.blog.dto.Notice;
import com.web.blog.mapper.NoticeMapper;

@Repository
public class NoticeDaoImpl implements NoticeDao{
	@Autowired
	NoticeMapper mapper;

	@Override
	public int count() throws Exception {
		return mapper.count();
	}

	@Override
	public List<Notice> searchAll(Map map) throws Exception {
		return mapper.selectAll(map);
	}

	@Override
	public Notice retrieve(int noticeId) throws Exception {
		return mapper.selectId(noticeId);
	}

	@Override
	public List<Notice> searchTitle(Map map) throws Exception {
		return mapper.selectTitle(map);
	}

	@Override
	public List<Notice> searchContent(Map map) throws Exception {
		return mapper.selectContent(map);
	}

	@Override
	public int regist(Notice notice) throws Exception {
		return mapper.insert(notice);
	}

	@Override
	public int modify(Notice notice) throws Exception {
		return mapper.update(notice);
	}

	@Override
	public int delete(int noticeId) throws Exception {
		return mapper.delete(noticeId);
	}

}
