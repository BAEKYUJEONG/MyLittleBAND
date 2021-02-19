package com.web.blog.mapper;

import java.util.HashMap;
import java.util.List;

import com.web.blog.dto.Like;
import com.web.blog.dto.LikeReq;

public interface LikeMapper {
	public void addlike(LikeReq req);
	public void unlike(String likeId);
	public Like getlikeById(LikeReq req);
	public List<HashMap<String, String>> likelist(String memberId);
	public HashMap<String, String> likecheck(String memberId, String boardId);
	public void likeup(String boardId);
	public void likedown(String likeId);
}