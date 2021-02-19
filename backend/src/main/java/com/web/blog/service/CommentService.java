package com.web.blog.service;

import java.util.List;

import com.web.blog.dto.Comment;
import com.web.blog.dto.OtherComment;

public interface CommentService {

	public boolean insert(Comment comment);
	public List<OtherComment> selectList(String boardId);
	public int count(String boardId);
	
}
