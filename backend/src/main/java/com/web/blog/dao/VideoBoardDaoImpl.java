//package com.web.blog.dao;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import com.web.blog.dto.VideoBoard;
//import com.web.blog.mapper.VideoBoardMapper;
//
//@Repository
//public class VideoBoardDaoImpl implements VideoBoardDao{
//
//	@Autowired
//	VideoBoardMapper mapper;
//	
//	@Override
//	public int insert(VideoBoard vboard) throws Exception {
//		return mapper.insert(vboard);
//	}
//
//	@Override
//	public int update(VideoBoard vboard) throws Exception {
//		return mapper.update(vboard);
//	}
//
//	@Override
//	public int delete(String boardId) throws Exception {
//		return mapper.delete(boardId);
//	}
//
//	@Override
//	public VideoBoard select(String boardId) throws Exception {
//		return mapper.select(boardId);
//	}
//
//	@Override
//	public List<VideoBoard> selectAll() throws Exception {
//		return mapper.selectAll();
//	}
//
//}
