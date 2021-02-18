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
	public HashMap<String, String> followcheck(String memberId, String bandId);
	public List<HashMap<String, String>> follownum(String bandId);
	public void followup(String bandId);
	public void followdown(String followId);
	public List<HashMap<String, String>> followrecommend(String memberId);
}
