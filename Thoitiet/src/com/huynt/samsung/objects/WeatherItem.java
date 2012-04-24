package com.huynt.samsung.objects;

import org.json.JSONException;
import org.json.JSONObject;

public class WeatherItem {
	public static final String KEY_3DAY_NEXT = "thoitiet3ngaytoi";
	public static final String KEY_24H_NEXT = "24h";
	public static final String KEY_48H_NEXT = "48h";
	public static final String KEY_72H_NEXT = "72h";
	
	public static final String KEY_DATE = "ngay";
	public static final String KEY_ICON = "icon";
	public static final String KEY_DES = "mota";
	public static final String KEY_TEMP = "nhietdo";
	
	private String mDate;
	private String mIcon;
	private String mDescription;
	private String mLowTemp;
	private String mHighTemp;
	
	public static WeatherItem getInstanceFromJSON(JSONObject object) throws JSONException{
		WeatherItem item = new WeatherItem();
		item.mDate  = object.getString(KEY_DATE);
		item.mIcon = object.getString(KEY_ICON);
		item.mDescription = object.getString(KEY_DES);
		String temp = object.getString(KEY_TEMP);
		String[] temps = temp.split(",");
		item.mLowTemp = temps[0] + "°C";
		item.mHighTemp = temps[1] + "°C";
		return item;
	}
	
	public String getDate(){
		return mDate;
	}
	
	public String getIcon(){
		return mIcon;
	}
	
	public String getDescription(){
		return mDescription;
	}
	
	public String getLowTemp(){
		return mLowTemp;
	}
	
	public String getHighTemp(){
		return mHighTemp;
	}
}
