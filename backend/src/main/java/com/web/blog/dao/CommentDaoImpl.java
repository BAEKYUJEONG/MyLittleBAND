package com.web.blog.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.blog.dto.Comment;
import com.web.blog.dto.OtherComment;
import com.web.blog.mapper.CommentMapper;

@Repository
public class CommentDaoImpl implements CommentDao {

	@Autowired
	CommentMapper mapper;
	
	@Override
	public boolean insert(Comment comment) {
		return mapper.insert(comment);
	}

	@Override
	public List<OtherComment> selectList(String boardId) {
		return mapper.selectList(boardId);
	}

	@Override
	public int count(String boardId) {
		return mapper.count(boardId);
	}

}
