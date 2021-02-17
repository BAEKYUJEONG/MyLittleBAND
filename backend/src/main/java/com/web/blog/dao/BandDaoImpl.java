package com.web.blog.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.blog.dto.Band;
import com.web.blog.dto.BandInvite;
import com.web.blog.dto.BandOpened;
import com.web.blog.dto.Crew;
import com.web.blog.dto.VideoBoard;
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
	public void addcrew(String memberId, String bandId, String codeSession) {
		mapper.addcrew(memberId, bandId, codeSession);
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

	@Override
	public List<HashMap<String, String>> memberofband(String memberId) {
		return mapper.memberofband(memberId);
	}

	@Override
	public void changeinfo(String bandId, String name, String intro, String genre, String color) {
		mapper.changeinfo(bandId, name, intro, genre, color);
	}

	@Override
	public void delband(String bandId) {
		mapper.delband(bandId);
	}

	@Override
	public void delcrew(String bandId) {
		mapper.delcrew(bandId);
	}

	@Override
	public void delfollow(String bandId) {
		mapper.delfollow(bandId);
	}

	@Override
	public void delcalendar(String bandId) {
		mapper.delcalendar(bandId);
	}

	@Override
	public void imgupdate(String bandId, String img) {
		mapper.imgupdate(bandId, img);
	}

	@Override
	public List<VideoBoard> videoofband(String bandId) {
		return mapper.videoofband(bandId);
	}

	@Override
	public List<HashMap<String, String>> searchband(String keyword) {
		return mapper.searchband(keyword);
	}

	@Override
	public void bandinvite(BandInvite req) {
		mapper.bandinvite(req);
	}

	
	
	
}
