package com.web.blog.mapper;

import java.util.List;

import com.web.blog.dto.Comment;
import com.web.blog.dto.OtherComment;

public interface CommentMapper {
	
	public boolean insert(Comment comment);
	public List<OtherComment> selectList(String boardId);
	public int count(String boardId);
}
