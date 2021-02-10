package com.web.blog.mapper;

import java.util.HashMap;
import java.util.List;

import com.web.blog.dto.Follow;
import com.web.blog.dto.FollowReq;

public interface FollowMapper {
	public void addfollow(FollowReq req);
	public void unfollow(String followId);
	public Follow getFollowById(FollowReq req);
	public List<HashMap<String, String>> followlist(String memberId);
	public HashMap<String, String> followcheck(String memberId, String bandId);
}
