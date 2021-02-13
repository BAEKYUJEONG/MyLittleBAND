package com.web.blog.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.blog.dto.Comment;
import com.web.blog.mapper.CommentMapper;

@Repository
public class CommentDaoImpl implements CommentDao {

	@Autowired
	CommentMapper mapper;
	
	@Override
	public boolean insert(Comment comment) {
		if(comment.getCommentId() == null) {
			mapper.insert(comment);
			return true;
		}
		return false;
	}

	@Override
	public Comment selectDetail(String commentId) {
		return mapper.selectDetail(commentId);
	}

	@Override
	public boolean update(Comment comment) {
		if(comment.getCommentId()!=null) {
			mapper.update(comment);
			return true;
		}
		return false;
	}

	@Override
	public void delete(String commentId) {
		mapper.delete(commentId);
	}

	@Override
	public List<Comment> selectList(Comment comment) {
		return mapper.selectList(comment);
	}

	@Override
	public int count() {
		return mapper.count();
	}

}
