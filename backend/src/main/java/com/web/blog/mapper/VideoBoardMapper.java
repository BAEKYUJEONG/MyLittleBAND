package com.web.blog.mapper;

import java.util.List;

import com.web.blog.dto.VideoBoard;

public interface VideoBoardMapper {
	public int insert(VideoBoard vboard) throws Exception;
	public int update(VideoBoard vboard) throws Exception;
	public int delete(String boardId) throws Exception;
	public VideoBoard select(String boardId) throws Exception;
	public List<VideoBoard> selectAll() throws Exception;
}
