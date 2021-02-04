package com.web.blog.service;

import java.util.HashMap;
import java.util.List;

import com.web.blog.dto.Like;
import com.web.blog.dto.LikeReq;

public interface LikeService {
	public void addlike(LikeReq req);
	public void unlike(String likeId);
	public Like getlikeById(LikeReq req);
	public List<HashMap<String, String>> likelist(String memberId);
}
