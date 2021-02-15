package com.web.blog.service;

import java.util.List;

import com.web.blog.dto.BandComment;
import com.web.blog.dto.Comment;
import com.web.blog.dto.OtherComment;

public interface BandCommentService {

	public boolean insert(BandComment comment);
	public List<OtherComment> selectList(String boardId);
	public int count(String boardId);
	public boolean delete(String commentId);
	public boolean modify(String commentId, String content);
	
}
