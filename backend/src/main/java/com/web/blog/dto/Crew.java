package com.web.blog.dto;

public class Crew {
	private String crewId;
	private String memberid;
	private String bandid;
	private boolean ischief;
	private String codesession;
	
	public Crew() {
	}

	public Crew(String crewId, String memberid, String bandid, boolean ischief, String codesession) {
		this.crewId = crewId;
		this.memberid = memberid;
		this.bandid = bandid;
		this.ischief = ischief;
		this.codesession = codesession;
	}

	public String getCrewId() {
		return crewId;
	}

	public void setCrewId(String crewId) {
		this.crewId = crewId;
	}

	public String getMemberid() {
		return memberid;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

	public String getBandid() {
		return bandid;
	}

	public void setBandid(String bandid) {
		this.bandid = bandid;
	}

	public boolean isIschief() {
		return ischief;
	}

	public void setIschief(boolean ischief) {
		this.ischief = ischief;
	}

	public String getCodesession() {
		return codesession;
	}

	public void setCodesession(String codesession) {
		this.codesession = codesession;
	}
	
	
	
	
	
}
