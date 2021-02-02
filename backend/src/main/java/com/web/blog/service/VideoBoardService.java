package com.web.blog.service;

import java.util.List;

import com.web.blog.dto.VideoBoard;

public interface VideoBoardService {
	
	public int regist(VideoBoard video) throws Exception;
	public int modify(VideoBoard video) throws Exception;
	public int remove(String boardId) throws Exception;
	
	public List<VideoBoard> searchAll() throws Exception;
}
