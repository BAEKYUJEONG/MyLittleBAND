package com.web.blog.mapper;

import java.util.HashMap;
import java.util.List;

import com.web.blog.dto.VideoBoard;
import com.web.blog.dto.VideoBoardReq;

public interface VideoBoardMapper {
	public void writevideo(VideoBoardReq req);
	public List<HashMap<String, String>> videoboardlist();
	public VideoBoard readvideo(String boardId);
	public void countup(String boardId);
	public void delvideo(String boardId);
	public void dellike(String boardId);
	public void changevideo(String boardId, String boardSubject, String boardContent);
	public void settime();
	public List<HashMap<String, String>> videoboardrankingview();
	public List<HashMap<String, String>> videoboardrankinglike();
	public List<HashMap<String, String>> videoboardrankingrookie();
	public List<HashMap<String, String>> videoboardrankingdaily();
}