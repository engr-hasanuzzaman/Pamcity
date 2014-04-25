package com.iqrasys.pamcity.model;

import java.util.ArrayList;

public class CategoryContainer {
	ArrayList<Category> data;
	public CategoryContainer() {
		 data = new ArrayList<Category>();
	}
	
//	return list 
	public ArrayList<Category> getCatList(){
		return this.data;
	}
}
