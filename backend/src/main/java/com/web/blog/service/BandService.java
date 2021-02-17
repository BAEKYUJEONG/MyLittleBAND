package com.web.blog.service;


import java.util.HashMap;
import java.util.List;

import com.web.blog.dto.Band;
import com.web.blog.dto.BandInvite;
import com.web.blog.dto.BandOpened;
import com.web.blog.dto.Crew;
import com.web.blog.dto.VideoBoard;

public interface BandService {
	public Band getBandByName(String name);
	public void opened(BandOpened req);
	public void addcrew(String memberId, String bandId, String codeSession);
	public HashMap<String, String> bandinfo(String bandId);
	public List<HashMap<String, String>> bandmember(String bandId);
	public Crew getCrewById(String crewId);
	public Crew getCrewBymemberId(String memberId);
	public void mandatechief(String crewId);
	public void receivechief(String crewId);
	public void withdrawband(String crewId);
	public void changesession(String crewId, String session);
	public List<HashMap<String, String>> memberofband(String memberId);
	public void changeinfo(String bandId, String name, String intro, String genre, String color);
	public void delband(String bandId);
	public void delcrew(String bandId);
	public void delfollow(String bandId);
	public void delcalendar(String bandId);
	public void imgupdate(String bandId, String img);
	public List<VideoBoard> videoofband(String bandId);
	public List<HashMap<String, String>> searchband(String keyword);
	public void bandinvite(BandInvite req);
}
