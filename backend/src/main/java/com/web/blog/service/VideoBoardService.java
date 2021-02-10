package com.web.blog.service;

import java.util.List;

import com.web.blog.dto.VideoBoard;

public interface VideoBoardService {
	
	public int insert(VideoBoard vboard) throws Exception;
	public int update(VideoBoard vboard) throws Exception;
	public int delete(String boardId) throws Exception;
	public VideoBoard select(String boardId) throws Exception;
	public List<VideoBoard> selectAll() throws Exception;
}
