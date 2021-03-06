package com.web.blog.service;

import java.util.HashMap;
import java.util.List;

import com.web.blog.dto.BandBoard;
import com.web.blog.dto.BandBoardReq;

public interface BandBoardService {
	public void writebandboard(BandBoardReq req);
	public List<HashMap<String, String>> bandboardlist(String bandId);
	public void settime();
	public BandBoard readbandboard(String bandboardId);
	public void countup(String bandboardId);
	public void delbandboard(String bandboardId);
	public void changebandboard(String bandboardId, String title, String content);
	
}
