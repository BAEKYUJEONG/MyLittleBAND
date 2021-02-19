package com.web.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.blog.dao.CommentDao;
import com.web.blog.dto.Comment;
import com.web.blog.dto.OtherComment;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	CommentDao dao;
	
	@Override
	public boolean insert(Comment comment) {
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
	
}
