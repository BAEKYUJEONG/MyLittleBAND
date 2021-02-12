package com.web.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.blog.dao.CommentDao;
import com.web.blog.dto.Comment;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	CommentDao dao;
	
	@Override
	public boolean insert(Comment comment) {
		if(comment.getCommentId() == null) {
			dao.insert(comment);
			return true;
		}
		return false;
	}

	@Override
	public Comment selectDetail(String commentId) {
		return dao.selectDetail(commentId);
	}

	@Override
	public boolean update(Comment comment) {
		if(comment.getCommentId()!=null) {
			dao.update(comment);
			return true;
		}
		return false;
	}

	@Override
	public void delete(String commentId) {
		dao.delete(commentId);
	}

	@Override
	public List<Comment> selectList(Comment comment) {
		return dao.selectList(comment);
	}

	@Override
	public int count() {
		return dao.count();
	}
	
}
