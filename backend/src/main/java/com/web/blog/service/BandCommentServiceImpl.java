package com.web.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.blog.dao.BandCommentDao;
import com.web.blog.dao.CommentDao;
import com.web.blog.dto.BandComment;
import com.web.blog.dto.Comment;
import com.web.blog.dto.OtherComment;

@Service
public class BandCommentServiceImpl implements BandCommentService{
	
	@Autowired
	BandCommentDao dao;

	@Override
	public boolean insert(BandComment comment) {
		return dao.insert(comment);
	}

	@Override
	public List<OtherComment> selectList(String boardId) {
		return dao.selectList(boardId);
	}

	@Override
	public int count(String boardId) {
		return dao.count(boardId);
	}

	@Override
	public boolean delete(String commentId) {
		return dao.delete(commentId);
	}

	@Override
	public boolean modify(String commentId, String content) {
		return dao.modify(commentId,content);
	}
	
}
