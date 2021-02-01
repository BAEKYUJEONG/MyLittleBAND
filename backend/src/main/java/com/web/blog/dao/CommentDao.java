package com.web.blog.dao;

import org.apache.ibatis.annotations.Mapper;

import com.web.blog.dto.Comment;

@Mapper
public interface CommentDao {
	public int insert(Comment comment) throws Exception;
	public int update(Comment comment) throws Exception;
	public int delete(String commentId) throws Exception;
}
