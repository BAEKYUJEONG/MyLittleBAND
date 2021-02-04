package com.web.blog.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.blog.dto.Band;
import com.web.blog.dto.BandOpened;
import com.web.blog.dto.Crew;
import com.web.blog.mapper.BandMapper;

@Repository
public class BandDaoImpl implements BandDao {
	@Autowired
	BandMapper mapper;

	@Override
	public Band getBandByName(String name) {
		return mapper.getBandByName(name);
	}

	@Override
	public void opened(BandOpened req) {
		mapper.opened(req);
	}

	@Override
	public void addcrew(String memberId, String bandId) {
		mapper.addcrew(memberId, bandId);
	}

	@Override
	public HashMap<String, String> bandinfo(String bandId) {
		return mapper.bandinfo(bandId);
	}

	@Override
	public List<HashMap<String, String>> bandmember(String bandId) {
		return mapper.bandmember(bandId);
	}

	@Override
	public Crew getCrewById(String crewId) {
		return mapper.getCrewById(crewId);
	}

	@Override
	public Crew getCrewBymemberId(String memberId) {
		return mapper.getCrewBymemberId(memberId);
	}

	@Override
	public void mandatechief(String crewId) {
		mapper.mandatechief(crewId);
	}

	@Override
	public void receivechief(String crewId) {
		mapper.receivechief(crewId);
	}

	@Override
	public void withdrawband(String crewId) {
		mapper.withdrawband(crewId);
	}

	@Override
	public void changesession(String crewId, String session) {
		mapper.changesession(crewId, session);
	}

	
	
	
}
