package com.iqrasys.pamcity.model;

public class Category {
	String Icon;
	int ID;
	String Name;
	
	public String getImageUrl() {
		return this.Icon;
	}
	public void setImageUrl(String url) {
		this.Icon = url;
	}
	public int getmId() {
		return this.ID;
	}
	public void setmId(int mId) {
		this.ID = mId;
	}
	public String getmTitle() {
		return this.Name;
	}
	public void setmTitle(String mTitle) {
		this.Name = mTitle;
	}

}
