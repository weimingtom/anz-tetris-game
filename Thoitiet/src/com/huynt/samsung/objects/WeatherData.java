package com.huynt.samsung.objects;

import java.util.ArrayList;

public class WeatherData {
	private ArrayList<WeatherItem> mListItem;
	private String mCityName;
	
	public WeatherData(String cityName){
		mListItem = new ArrayList<WeatherItem>();
		mCityName = cityName;
	}
	
	public void add(WeatherItem item){
		mListItem.add(item);
	}
	
	public ArrayList<WeatherItem> getListWeather(){
		return mListItem;
	}
	
	public String getCityName(){
		return mCityName;
	}
}
