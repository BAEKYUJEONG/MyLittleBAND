package com.web.blog.mapper;


import java.util.HashMap;
import java.util.List;

import com.web.blog.dto.Band;
import com.web.blog.dto.BandOpened;
import com.web.blog.dto.Crew;

public interface BandMapper {
	public Band getBandByName(String name);
	public void opened(BandOpened req);
	public void addcrew(String memberId, String bandId);
	public HashMap<String, String> bandinfo(String bandId);
	public List<HashMap<String, String>> bandmember(String bandId);
	public Crew getCrewById(String crewId);
	public Crew getCrewBymemberId(String memberId);
	public void mandatechief(String crewId);
	public void receivechief(String crewId);
	public void withdrawband(String crewId);
	public void changesession(String crewId, String session);
}
