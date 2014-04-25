package com.iqrasys.pamcity.model;

import java.util.ArrayList;

/**
 * @author s
 * Used as base for json parsing using Gson
 */
public class CountryCityContainer {
	ArrayList<CountryCity> data; // conatiner node of json string is data
	public CountryCityContainer() {
		 data = new ArrayList<CountryCity>();
	}
	
//	return list 
	public ArrayList<CountryCity> getCountryCityList(){
		return this.data;
	}
	public ArrayList<String> countrtyNameList() {
		ArrayList<String> list=new ArrayList<String>();
		list.add("Select CountryCity");	
		for(int i=0;i<this.data.size();i++){
			list.add(data.get(i).getName());			
		}
		return list;
		
	}
	public ArrayList<String> cityNameList() {
		ArrayList<String> list=new ArrayList<String>();
		list.add("Select City");	
		for(int i=0;i<this.data.size();i++){
			list.add(data.get(i).getName());			
		}
		return list;
		
	}
}
