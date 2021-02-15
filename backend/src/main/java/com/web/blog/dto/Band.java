package com.web.blog.dto;

public class Band {
	private String bandId;
	private String rolecode;
	private String colorid;
	private String img;
	private String name;
	private String intro;
	private String local;
	private String debut;
	private String genre;
	private String sns;
	private String follownum;
	
	public Band() {
	}


	public Band(String bandId, String rolecode, String colorid, String img, String name, String intro, String local,
			String debut, String genre, String sns, String follownum) {
		this.bandId = bandId;
		this.rolecode = rolecode;
		this.colorid = colorid;
		this.img = img;
		this.name = name;
		this.intro = intro;
		this.local = local;
		this.debut = debut;
		this.genre = genre;
		this.sns = sns;
		this.follownum = follownum;
	}




	public String getBandId() {
		return bandId;
	}

	public void setBandId(String bandId) {
		this.bandId = bandId;
	}

	public String getRolecode() {
		return rolecode;
	}

	public void setRolecode(String rolecode) {
		this.rolecode = rolecode;
	}

	public String getColorid() {
		return colorid;
	}

	public void setColorid(String colorid) {
		this.colorid = colorid;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getDebut() {
		return debut;
	}

	public void setDebut(String debut) {
		this.debut = debut;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getSns() {
		return sns;
	}

	public void setSns(String sns) {
		this.sns = sns;
	}


	public String getFollownum() {
		return follownum;
	}


	public void setFollownum(String follownum) {
		this.follownum = follownum;
	}
	
	
}
