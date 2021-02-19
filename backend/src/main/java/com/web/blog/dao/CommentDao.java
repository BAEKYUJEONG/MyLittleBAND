package com.web.blog.dao;

import java.util.List;

import com.web.blog.dto.Comment;
import com.web.blog.dto.OtherComment;

public interface CommentDao {
	
	public boolean insert(Comment comment);
	public List<OtherComment> selectList(String boardId);
	public int count(String boardId);
	
}
