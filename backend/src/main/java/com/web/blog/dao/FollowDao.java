package com.web.blog.dao;

import java.util.HashMap;
import java.util.List;

import com.web.blog.dto.Follow;
import com.web.blog.dto.FollowReq;

public interface FollowDao {
	public void addfollow(FollowReq req);
	public void unfollow(String followId);
	public Follow getFollowById(FollowReq req);
	public List<HashMap<String, String>> followlist(String memberId);
}
