package com.web.blog.dto;

public class BandInvite {
	private String memberId;
	private String bandId;
	private String codeSession;
	
	public BandInvite() {
	}

	public BandInvite(String memberId, String bandId, String codeSession) {
		this.memberId = memberId;
		this.bandId = bandId;
		this.codeSession = codeSession;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getBandId() {
		return bandId;
	}

	public void setBandId(String bandId) {
		this.bandId = bandId;
	}

	public String getCodeSession() {
		return codeSession;
	}

	public void setCodeSession(String codeSession) {
		this.codeSession = codeSession;
	}

	
	
}
