package com.web.blog.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.blog.dto.BandComment;
import com.web.blog.dto.Comment;
import com.web.blog.dto.OtherComment;
import com.web.blog.mapper.BandCommentMapper;
import com.web.blog.mapper.CommentMapper;

@Repository
public class BandCommentDaoImpl implements BandCommentDao {

	@Autowired
	BandCommentMapper mapper;
	
	@Override
	public boolean insert(BandComment comment) {
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

	@Override
	public boolean delete(String commentId) {
		return mapper.delete(commentId);
	}

	@Override
	public boolean modify(String commentId, String content) {
		return mapper.modify(commentId,content);
	}

}
