package com.web.blog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.blog.dto.VideoBoard;

@Mapper
public interface VideoBoardDao {
	
	public int insert(VideoBoard vboard) throws Exception;
	public int update(VideoBoard vboard) throws Exception;
	public int delete(String board_id) throws Exception;
	public VideoBoard select(String board_id) throws Exception;
	public List<VideoBoard> selectAll() throws Exception;
}
