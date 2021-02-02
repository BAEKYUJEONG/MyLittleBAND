package com.web.blog.service;

import com.web.blog.dto.Comment;

public interface CommentService {
	
	public int regist(Comment comment) throws Exception;
	public int modify(Comment comment) throws Exception;
	public int delete(String commentId) throws Exception;
}
