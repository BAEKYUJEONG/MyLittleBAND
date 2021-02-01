package com.web.blog.dto;

public class Crew {
	private String id;
	private String memberid;
	private String bandid;
	private String ischief;
	private String codesession;
	
	public Crew() {
	}

	public Crew(String id, String memberid, String bandid, String ischief, String codesession) {
		this.id = id;
		this.memberid = memberid;
		this.bandid = bandid;
		this.ischief = ischief;
		this.codesession = codesession;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getIschief() {
		return ischief;
	}

	public void setIschief(String ischief) {
		this.ischief = ischief;
	}

	public String getCodesession() {
		return codesession;
	}

	public void setCodesession(String codesession) {
		this.codesession = codesession;
	}
	
	
	
}
